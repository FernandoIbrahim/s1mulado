const { DataTypes } = require('sequelize');
const sequelize = require('../Sequelize'); // Ajuste o caminho se necessário

const Question = sequelize.define('Question', {
  id: {
    type: DataTypes.BIGINT,
    autoIncrement: true,
    primaryKey: true,
  },
  alternativesIntroduction: {
    type: DataTypes.TEXT,
    field: 'alternatives_introduction',
    allowNull: true,
  },
  context: {
    type: DataTypes.TEXT,
    allowNull: true,
  },
  correctAnswer: {
    type: DataTypes.TEXT,
    field: 'correct_awnser',
    allowNull: true,
  },
  knowledgeArea: {
    type: DataTypes.STRING(255),
    allowNull: true,
    validate: {
      isIn: [['NATURE', 'HUMANITIES', 'MATHEMATICS', 'LANGUAGES']],
    },
    field: 'knowledge_area',
  },
  title: {
    type: DataTypes.TEXT,
    allowNull: true,
  },
  year: {
    type: DataTypes.BIGINT,
    allowNull: true,
  },
  subjectId: {
    type: DataTypes.BIGINT,
    allowNull: true,
    references: {
      model: 'subjects', // Nome da tabela referenciada
      key: 'id',
    },
    field: 'subject_id',
  },
}, {
  tableName: 'questions',
  timestamps: false, // Ajuste se houver colunas de timestamp como 'createdAt' ou 'updatedAt'
});

module.exports = Question;
