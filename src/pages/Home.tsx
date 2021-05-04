import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  IonButton,
} from '@ionic/react';

import Echo from '../native/echoplugin';
import './Home.css';

const Home: React.FC = () => {
  const callNative = async () =>{
    const { value } = await Echo.echo({value: 'Hello World'});
    console.log("Response from Native: ", value);
  }

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Blank</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Blank</IonTitle>
          </IonToolbar>
        </IonHeader>
        <IonButton onClick={callNative}>Click and Log</IonButton>
      </IonContent>
    </IonPage>
  );
};

export default Home;
