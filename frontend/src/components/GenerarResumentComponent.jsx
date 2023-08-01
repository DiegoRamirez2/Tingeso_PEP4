import React, { useState } from "react";
import styled from "styled-components";
import { createGlobalStyle } from "styled-components";
import bgImage from "../resources/fondo.jpg";
import MovimientoService from "./ObtenerResumen";
import swal from "sweetalert";
import Logo2 from "../resources/logo2.png";

export default function GenerarResumenComponent() {
  const [fechainicio, setFechainicio] = useState("");
  const [fechafin, setFechafin] = useState("");
  const [movimientos, setMovimientos] = useState([]);

  const obtenerResumen = () => {
    if (!fechainicio || !fechafin || fechainicio > fechafin) {
      swal("Por favor, asegúrate de que las fechas sean válidas y que la Fecha de Inicio sea anterior a la Fecha Fin.", { icon: "error", timer: "3000" });
      return;
    }
    console.log(fechainicio, fechafin)
    MovimientoService.getMovimientosEntreFechas(fechainicio, fechafin)
      .then((data) => {
        if (data.length === 0) {
          swal("No se encontraron movimientos entre las fechas especificado.", { icon: "error", timer: "3000" });
        } else {
          setMovimientos(data);
        }
      });
  };

  return (
    <div className="home">
      <GlobalStyle />
      <ResumenStyle>
        <div className="main-text">
          {"Cash Balance".split("").map((char, index) => (
            <span key={index}>{char}</span>
          ))}
          <img src={Logo2} alt="Logo2" className="logo" style={{ width: "80px", height: "auto", marginLeft: "5px", verticalAlign: "top", marginTop: "15px" }} />
        </div>
        <button className="return-button" onClick={() => window.location.href = '/'}>Volver al menú principal</button>
        <div className="resumen">
          <div className="text-center">
            <h1><b>Resumen de movimientos</b></h1>
          </div>
          <div className="fechas">
            <div className="ancho movimientoForm-px-3 espacio-campos">
              <label htmlFor="fechainicio" className="movimientoForm-form-label"><b>Fecha Inicio:</b></label>
              <input type="date" name="fechainicio" id="fechainicio" className="movimientoForm-form-input" value={fechainicio} onChange={(e) => setFechainicio(e.target.value)} />
            </div>
            <div className="ancho movimientoForm-px-3 espacio-campos">
              <label htmlFor="fechafin" className="movimientoForm-form-label"><b>Fecha Fin:</b></label>
              <input type="date" name="fechafin" id="fechafin" className="movimientoForm-form-input" value={fechafin} onChange={(e) => setFechafin(e.target.value)} />
            </div>
          </div>
          <div className="button-container">
            <button onClick={obtenerResumen}>Obtener resumen</button>
          </div>
        </div>
        <table border="1" className="content-table">
          {movimientos.length > 0 && (
            <thead>
              <tr>
                <th>Nro</th>
                <th>Fecha</th>
                <th>Tipo Doc</th>
                <th>Num Doc</th>
                <th>Motivo</th>
                <th>Ingreso</th>
                <th>Salida</th>
                <th>Saldo</th>
              </tr>
            </thead>
          )}
          <tbody>
            {movimientos.map((movimiento) => (
              <tr key={movimiento.idMovimiento} className="content-row">
                <td>{movimiento.idMovimiento}</td>
                <td>{movimiento.fecha}</td>
                <td>{movimiento.tipoDoc}</td>
                <td>{movimiento.numDoc}</td>
                <td>{movimiento.motivo}</td>
                <td>{movimiento.ingreso}</td>
                <td>{movimiento.salida}</td>
                <td>{movimiento.saldo}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </ResumenStyle>
    </div>
  );
}

const GlobalStyle = createGlobalStyle`
body{
  background-color: #262626;
  background-image: url(${bgImage});
  background-repeat: no-repeat;
  background-size: cover;
  background-attachment: fixed;
  min-height: 100vh;
  }
`;


const ResumenStyle = styled.div`
  .text-center {
    text-align: center;
    justify-content: center;
    padding-top: 8px;
  }

  .main-text {
    text-align: left; 
    font-size: 80px;
    color: white;
    -webkit-text-stroke: 2px white;
    position: relative;
    overflow: hidden;
    padding: 8px; 
  }

  .resumen{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-end;
    width: fit-content; 
    border-radius: 15px;
    background-color: #ffffff;
    margin: 0 auto;
    padding: 15px;
    transition: .3s;
    margin-top: 25px;
    margin-bottom: 25px;
    border: 4px solid #46B63A;
}

.ancho {
  width: 100%;
}

.movimientoForm-px-3 {
  padding-left: 12px;
  padding-right: 12px;
}

.espacio-campos {
  margin-bottom: 20px;
}

.movimientoForm-form-label {
  display: block;
  font-weight: 500;
  font-size: 16px;
  color: #07074d;
  margin-bottom: 12px;
}

.movimientoForm-form-input {
  width: 100%;
  padding: 12px 24px;
  border-radius: 10px;
  border: 1px solid #e0e0e0;
  background: #DADDD8;
  font-weight: 500;
  font-size: 16px;
  color: #6b7280;
  outline: none;
  resize: none;
}

.movimientoForm-form-input:focus {
  border-color: #6a64f1;
  box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
}


.content-table tbody tr:nth-of-type(odd) {
  background-color: #f3f3f3;
}


.fecha-inicio, .fecha-fin {
  width: 100%;
  padding: 12px 24px;
  border-radius: 10px;
  border: 1px solid #e0e0e0;
  background: #DADDD8;
  font-weight: 500;
  font-size: 16px;
  color: #6b7280;
  outline: none;
  resize: none;
}

.fecha-inicio:focus, .fecha-fin:focus {
  border-color: #6a64f1;
  box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
}


.table-container {
  display: flex;
  justify-content: center;
  align-items: center;
}


  .fechas {
    flex-direction: column;
    display: flex;
  }

  * {
    font-family: sans-serif;
    box-sizing: border-box;
    margin: 0;
    padding: 0;
  }

  .content-table {
  border-radius: 25px;
  margin: 0 auto;
  border-collapse: collapse;
  font-size: 1em; 
  width: 80%; 
  border-radius: 5px 5px 0 0;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  margin-bottom: 20px;
  }

  .content-table thead tr {
    background-color: #009879;
    color: #ffffff;
    text-align: center;
    font-weight: bold;
  }

.content-table th,
.content-table td {
  padding: 12px 5px; 
  word-wrap: break-word; 
}

.content-table th:nth-of-type(5), 
.content-table td:nth-of-type(5) {
  max-width: 200px; /* Establece un ancho máximo para la columna "Motivo" */
}

  .content-table tbody tr {
    border-bottom: 1px solid #dddddd;
  }

  .content-table tbody tr:nth-of-type(even) {
    background-color: #f3f3f3;
  }

  .content-table tbody tr:last-of-type {
    border-bottom: 2px solid #009879;
  }

  .content-table tbody tr.active-row {
    font-weight: bold;
    color: #009879;
  }

  
  .button-container {
    text-align: center;
    margin-top: 16px;
  }

  button {
    border-radius: 20px;
    background-color: #46B63A;
    color: white;
    padding: 8px 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  button:hover {
    background-color: #5cbf2a;
  }
}

.return-button {
  position: absolute;
  top: 20px;
  right: 20px;
  background-color: #46B63A;
  border: 4px solid #000;
  color: white;
  border: none;
  padding: 10px 20px;
  font-size: 25px;
  cursor: pointer;
  border-radius: 25px;
}

.return-button:hover {
  background-color: #5cbf2a;
  border-color: #418a22;
}
`;


