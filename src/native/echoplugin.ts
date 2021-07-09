import { registerPlugin } from '@capacitor/core';
export interface EchoPlugin {
  login(options: { value: string }): Promise<{ value: string }>;
  echo(options: { value: string }): Promise<{ value: string }>;
}
const Echo = registerPlugin<EchoPlugin>('Echo');
export default Echo;
