import Axios from "axios";

const URL = "http://localhost:8080/";

class RegistrarService{
  createMovimiento(tipo, movimiento){
    return Axios.post(URL + tipo, movimiento);
  }

  existeMovimiento(numDoc){
    return Axios.get(URL + "movimientos/" + numDoc);
  }
}

const registroServiceInstance = new RegistrarService();
export default registroServiceInstance;
