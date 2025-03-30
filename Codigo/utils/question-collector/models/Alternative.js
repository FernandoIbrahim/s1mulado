const { DataTypes } = require('sequelize');
const sequelize = require('../Sequelize'); // Ajuste para o caminho correto do arquivo de conexão ao banco

const Alternative = sequelize.define(
  'Alternative',
  {
    id: {
      type: DataTypes.BIGINT,
      autoIncrement: true,
      primaryKey: true,
    },
    imageUrl: {
      type: DataTypes.TEXT,
      field: 'image_url',
      allowNull: true,
    },
    isCorrect: {
      type: DataTypes.BOOLEAN,
      field: 'is_correct',
      allowNull: true,
    },
    letter: {
      type: DataTypes.TEXT,
      allowNull: true,
    },
    text: {
      type: DataTypes.TEXT,
      allowNull: true,
    },
    questionId: {
      type: DataTypes.BIGINT,
      field: 'question_id',
      allowNull: true,
      references: {
        model: 'questions', // Nome da tabela referenciada
        key: 'id',
      },
    },
  },
  {
    tableName: 'alternatives',
    timestamps: false,
  }
);

module.exports = Alternative;
