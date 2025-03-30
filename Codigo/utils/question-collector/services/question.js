async function questionLinkChanger(question) {
    let updated = false;
    const regex = /https:\/\/enem\.dev\/\d{4}\/questions\/[a-zA-Z0-9-]+\/([a-zA-Z0-9-]+(?:\.[a-z]+)?)/g;

    function processValue(value) {
        if (typeof value === 'string') {
            return value.replace(regex, 'https://s1mulado.s3.amazonaws.com/imagens/$1');
        } else if (Array.isArray(value)) {
            return value.map(item => processValue(item));
        }
        return value;
    }

    // Processa o objeto principal (compatível com dataValues ou objetos simples)
    const target = question.dataValues || question;
    
    for (const key in target) {
        const originalValue = target[key];
        const newValue = processValue(originalValue);
        
        if (newValue !== originalValue) {
            target[key] = newValue;
            updated = true;
        }
    }

    console.log("Links atualizados na questão:", target.context);
    return question;
}

module.exports = questionLinkChanger;