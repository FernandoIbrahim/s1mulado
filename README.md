# 📚 s1mulado

**s1mulado** é uma plataforma gratuita para a realização de **simulados do ENEM**, voltada para estudantes que desejam praticar com questões reais de provas anteriores. A aplicação tem como objetivo **democratizar o acesso à educação**, oferecendo um ambiente prático, intuitivo e fiel ao exame oficial.

As questões são organizadas por áreas do conhecimento e categorizadas de acordo com os **principais temas cobrados em cada disciplina**, permitindo uma experiência de simulado realista e direcionada.

---

## 🚀 Tecnologias Utilizadas

### Frontend
- [Vue.js](https://vuejs.org/)
- [Vite](https://vitejs.dev/)
- [Tailwind CSS](https://tailwindcss.com/)

### Backend
- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/)

### Question Collector
Responsável por coletar as questões da API pública do ENEM, converter os dados para o formato da aplicação, classificar os temas automaticamente com auxílio da IA e enviar as imagens para um storage próprio.

- [Sequelize](https://sequelize.org/)
- [ChatGPT (OpenAI)](https://openai.com/)

### Outros
- [AWS S3](https://aws.amazon.com/s3/) — Armazenamento de imagens públicas

---

## 🧩 Funcionalidades

- 🧠 **Realização de simulados com questões reais do ENEM**
- 📊 **Pontuação automática e feedback imediato**
- 🔍 **Análise de provas anteriores por tema e disciplina**
- ☁️ **Importação automatizada de questões e imagens via API pública**
- 🏷️ **Classificação inteligente dos temas de cada questão**

---