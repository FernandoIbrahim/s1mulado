// Carrega as variáveis de ambiente do arquivo .env
require('dotenv').config();

// Acesse a chave da API de forma segura
const API_KEY = process.env.API_KEY;

async function getSubject(prompt) {

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
                { role: "system", content: "Classifique questão com base no id das matérias micro: 1- ECOLOGIA, 2- FISIOLOGIA HUMANA, 3- GENETICA E BIOTECNOLOGIA, 4- CITOLOGIA, 5- QUIMICA ORGANICA, 6- ESTRUTURA ATOMICA, 7- PROPRIEDADE DOS MATERIAIS, 8- ONDULATORIA, 9- ELETRICIDADE E MAGNETISMO, 10- MECANICA, 11- QUIMICA, 12- FISICA, 13- BIOLOGIA, 17- EVOLUCAO E ORIGEM DA VIDA, 18- BOTANICA E ZOOLOGIA, 19- MICROBIOLOGIA E IMUNOLOGIA, 20- ESTEQUIOMETRIA, 21- TERMOQUIMICA, 22- ELETROQUIMICA, 23- QUIMICA AMBIENTAL, 24- OPTICA, 25- TERMODINAMICA, 26- FISICA MODERNA e retorne apenas o id:" },
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
        console.log(message);
        return message;
    } catch (error) {
        console.error("Erro ao acessar a API do ChatGPT:", error);
    }


}



module.exports = getSubject;