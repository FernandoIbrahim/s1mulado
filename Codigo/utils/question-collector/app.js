const axios = require('axios');
const Question = require('./models/Question');
const Alternative = require('./models/Alternative');

const getFormattedQuestion = require('./chatgpt/chatgpt');
const { downloadAndUploadAllImages }  = require('./services/image');
const questionLinkChanger = require('./services/question');
const {fetchQuestions } = require('./services/apiService');

async function getQuestions() {


    const questions = await fetchQuestions();

    await delay(1000);
    for (var question of questions) {

      console.log(question.files);

      if(question.files != []){

        await downloadAndUploadAllImages(question.files);
        await delay(1000);
        question = await questionLinkChanger(question);
  
      }

      question = await getFormattedQuestion(JSON.stringify(question));

      try {

        const createdQuestion = await Question.create({
          alternativesIntroduction: question.alternativesIntroduction,
          context: question.context,
          correctAnswer: question.correctAlternative,
          knowledgeArea: "NATURE",
          title: question.title,
          year: question.year,
          subjectId: question.subject_id, // Ajustar se necessário
        });



        for (const alternative of question.alternatives) {
          await Alternative.create({
            imageUrl: alternative.file, // Considerando que o campo `file` é o URL da imagem
            isCorrect: alternative.isCorrect || false,
            letter: alternative.letter,
            text: alternative.text,
            questionId: createdQuestion.id,
          });
        }


        console.log(`Questão ${question.index} e suas alternativas criadas com sucesso.`);

        console.log("timeout 2s");
        await delay(2000); // Aguarda 5 segundos


      } catch (err) {

        console.error(`Erro ao criar a questão ${question.index}:`, err.message);
        
      }

    }

}




function delay(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}




getQuestions();





function mapDisciplineToKnowledgeArea(discipline) {
  const knowledgeAreaMap = {
    "linguagens": 'LANGUAGES',
    "ciencias-natureza": 'NATURE',
    humanidades: 'HUMANITIES',
    matematica: 'MATHEMATICS',
  };
  return knowledgeAreaMap[discipline] || null;

}

