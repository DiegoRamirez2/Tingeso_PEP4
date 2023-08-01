import Axios from "axios";

const URL = "http://127.0.0.1:7982/";
const URL2 = "http://localhost:8080/";

class MovimientoService {
  getMovimientosEntreFechas(fechainicio, fechafin) {
    return Axios.get(URL +`movimientos/getEntreFechas/${fechainicio}/${fechafin}`)
      .then((response) => response.data) // Esto asegura que estás retornando la data directamente
      .catch((error) => {
        console.error("Error al obtener los movimientos entre fechas:", error);
        return []; // Retornar un array vacío en caso de error podría ser útil para manejar el error más adelante
      });
  }
}


const movimientoServiceInstance = new MovimientoService();
export default movimientoServiceInstance;