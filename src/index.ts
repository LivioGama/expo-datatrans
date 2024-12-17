// Reexport the native module. On web, it will be resolved to ExpoDatatransModule.web.ts
// and on native platforms to ExpoDatatransModule.ts
export { default } from './ExpoDatatransModule';
export { default as ExpoDatatransView } from './ExpoDatatransView';
export * from  './ExpoDatatrans.types';
