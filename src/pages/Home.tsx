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
  const addTask = async () =>{
    const { value } = await Echo.echo({value: 'Add Task'});
    console.log("addTask Response from Native: ", value);
  }

  const loginToRealm = async () =>{
    console.log("Calling native Login");
    const { value }  = await Echo.login({value: 'Login Realm'});
    console.log("loginToRealm Response from Login: ", value);
  }

  const test = async () =>{
    console.log("Test");
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

        <p><IonButton onClick={addTask}>Click and Create a Task</IonButton></p>
      </IonContent>
    </IonPage>
  );
};

export default Home;
