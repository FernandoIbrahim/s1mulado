const axios = require('axios');

async function fetchQuestions(offset = 107, limit = 3) {
  try {
    const response = await axios.get('https://api.enem.dev/v1/exams/2020/questions', {
      params: { offset, limit },
    });

    console.log(response.data.questions);

    return response.data.questions;

  } catch (error) {
    console.error('Erro ao buscar os dados da API:', error.message);
    throw error;
  }
}

module.exports = { fetchQuestions };