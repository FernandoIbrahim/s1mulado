// Carrega as variáveis de ambiente do arquivo .env
require('dotenv').config();

// Acesse a chave da API de forma segura
const API_KEY = process.env.API_KEY;

async function getFormattedQuestion(prompt) {

    const url = "https://api.openai.com/v1/chat/completions";

    // Configuração da requisição
    const options = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${API_KEY}`
        },
        body: JSON.stringify({
            model: "gpt-4", // ou "gpt-4" se você tiver acesso
            messages: [
                { role: "system", content: "## Objetivo Você é um assistente especializado em processar questões de múltipla escolha. Sua tarefa é receber um objeto de questão no formato JSON e modificar seu campo 'subject_id' com base em uma lista predefinida de categorias. Além disso, você deve garantir que os campos 'alternatives_introduction' e 'context' não contenham quebras de linha apenas para mostrar imagens em caso de uma referência bibliografica ou no início de outro paragrafo. ## Lista de Valores para 'subject_id' 1- ECOLOGIA | 2- FISIOLOGIA HUMANA | 3- GENÉTICA E BIOTECNOLOGIA | 4- CITOLOGIA | 5- QUÍMICA ORGÂNICA | 6- ESTRUTURA ATÔMICA | 7- PROPRIEDADE DOS MATERIAIS | 8- ONDULATÓRIA | 9- ELETRICIDADE E MAGNETISMO | 10- MECÂNICA | 11- QUÍMICA | 12- FÍSICA | 13- BIOLOGIA | 17- EVOLUÇÃO E ORIGEM DA VIDA | 18- BOTÂNICA E ZOOLOGIA | 19- MICROBIOLOGIA E IMUNOLOGIA | 20- ESTEQUIOMETRIA | 21- TERMOQUÍMICA | 22- ELETROQUÍMICA | 23- QUÍMICA AMBIENTAL | 24- ÓPTICA | 25- TERMODINÂMICA | 26- FÍSICA MODERNA## Instruções para a IA Sua missão é analisar o contexto da questão e definir o 'subject_id' mais adequado. Retorne apenas o número correspondente. Por exemplo, dado o objeto acima, o 'subject_id' correto seria **23** (Química Ambiental), pois a questão trata de aquecimento global e efeito estufa. Se houver ambiguidade, escolha o número que melhor representa o conteúdo da questão. Caso o assunto não esteja diretamente relacionado a nenhuma das categorias disponíveis, retorne ou 13 (Biologia) ou 12 (Quimica) ou 11 (Fisica), pois é uma categoria mais abrangente. Você deve retornar o JSON do novo objeto com os textos formatados e o subject_id alterado" },
                { role: "user", content: prompt }
            ]
        })
    };

    try {
        const response = await fetch(url, options);

        if (!response.ok) {
            throw new Error(`Erro: ${response.status}`);
        }

        const data = await response.json();

        // Extrai a resposta do assistente
        const message = data.choices[0].message.content;
        console.log(message)
        return JSON.parse(message);

    } catch (error) {
        console.error("Erro ao acessar a API do ChatGPT:", error);
    }


}





module.exports = getFormattedQuestion;

