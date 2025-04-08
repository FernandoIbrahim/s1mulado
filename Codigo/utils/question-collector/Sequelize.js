const { Sequelize, DataTypes } = require('sequelize'); // Corrige o require de 'sequelize'

const sequelize = new Sequelize('s1mulado_db', 'user', 'password', { // Adicionado o 'new' no Sequelize
  host: 'localhost',
  dialect: 'postgres',
  logging: false, // Defina como true para exibir as queries no console
});

module.exports = sequelize;
