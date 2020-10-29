/*
module.exports = {
  preset: 'ts-jest',
  testEnvironment: 'node',
};
*/

// see https://basarat.gitbook.io/typescript/intro-1/jest
//export default {
module.exports = {
  "roots": [
    "<rootDir>/src"
  ],
  "testMatch": [
    "**/__tests__/**/*.+(ts|tsx|js)",
    "**/?(*.)+(spec|test).+(ts|tsx|js)"
  ],
  "transform": {
    "^.+\\.(ts|tsx)$": "ts-jest"
  },
}