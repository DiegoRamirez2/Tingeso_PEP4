import Axios from "axios";

const URL = "http://127.0.0.1:9045/";
const URL2 = "http://localhost:8080/";

class RegistrarService{
  createMovimiento(tipo, movimiento){
    return Axios.post(URL2 + tipo, movimiento);
  }

  existeMovimiento(numDoc){
    return Axios.get(URL2 + "movimientos/numDoc/" + numDoc);
  }
}

const registroServiceInstance = new RegistrarService();
export default registroServiceInstance;
