import Axios from "axios";

const URL = "http://127.0.0.1:7982/";
const URL2 = "http://localhost:8080/";

class RegistrarService{
  createMovimiento(tipo, movimiento){
    return Axios.post(URL + tipo, movimiento);
  }

  existeMovimiento(numDoc){
    return Axios.get(URL + "movimientos/numDoc/" + numDoc);
  }
}

const registroServiceInstance = new RegistrarService();
export default registroServiceInstance;
