import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  IonButton,
  IonLabel
} from '@ionic/react';

import Echo from '../native/echoplugin';
import './Home.css';

const Home: React.FC = () => {
  const callNative = async () =>{
    const { value } = await Echo.echo({value: 'Hello World'});
    console.log("Response from Native: ", value);
  }

  const loginToRealm = async () =>{
    const { value }  = await Echo.login({value: 'Hello World'});
    console.log("Response from Login: ", value);
  }


  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Realm Test</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Realm Test</IonTitle>
          </IonToolbar>
        </IonHeader>
        <p><IonLabel>You need to 1st log in to Realm</IonLabel></p>
        <p><IonButton onClick={loginToRealm}>Login to Realm</IonButton></p>

        <p><IonButton onClick={callNative}>Click and Log</IonButton></p>
      </IonContent>
    </IonPage>
  );
};

export default Home;
