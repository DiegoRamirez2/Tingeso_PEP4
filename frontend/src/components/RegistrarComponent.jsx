import React from 'react';
import styled from 'styled-components';
import { createGlobalStyle } from 'styled-components';
import bgImage from '../resources/fondo.jpg';
import swal from 'sweetalert';
import Logo2 from '../resources/logo2.png';
import RegistrarService from './RegistrarService';

export default function RegistrarComponent() {
  const [fecha, setFecha] = React.useState(new Date());
  const [tipoDoc, setTipoDoc] = React.useState('');
  const [numeroDoc, setNumeroDoc] = React.useState('');
  const [motivo, setMotivo] = React.useState('');
  const [cantidad, setCantidad] = React.useState('');

  const changeTipoDocHandler = event => {
    setTipoDoc(event.target.value);
  }
  const changeNumDocHandler = event => {
    setNumeroDoc(event.target.value);
  }
  const changeMotivoHandler = event => {
    setMotivo(event.target.value);
  }
  const changeCantidadHandler = (event) => {
    const value = event.target.value;
    const floatVal = parseFloat(value);
  
    if (isNaN(floatVal)) {
      setCantidad('');
    } else {
      setCantidad(floatVal);
    }
  };
  const changeFechaHandler = event => {
    setFecha(event.target.value);
  }

  const validarForm = () => {
    if (!fecha || !tipoDoc || !numeroDoc || !motivo || !cantidad) {
      swal("Por favor, llena todos los campos", { icon: "error", timer: "3000" });
      return false;
    } else {
      RegistrarService.existeMovimiento(numeroDoc)
        .then((response) => {
          if (response.data === true) {
            swal("El movimiento ya existe, ingrese otro número de documento", { icon: "error", timer: "3000" });
          } else {
          }
        })
        .catch((error) => {
          console.error("Error al hacer la solicitud GET:", error);
          // Manejar el error en caso de que la solicitud falle
          // ...
        });
      return true;
    }
  };

  const saveMovimiento = e => {
    e.preventDefault();
    if (!validarForm()) {
      return;
    }
    swal({
      title: "¿Estás seguro que desea registrar el movimiento?",
      text: "Una vez registrado, no podrá modificarlo",
      icon: "warning",
      buttons: ["Cancelar", "Aceptar"],
      dangerMode: true,
    }).then(respuesta => {
      if (respuesta) {
        swal("Se ha registrado el movimiento con éxito!", { icon: "success", timer: "3000" });
        let movimientoFactura = { fecha: fecha, tipoDoc: tipoDoc, numDoc: numeroDoc, motivo: motivo, salida: cantidad };
        let movimientoBoleta = { fecha: fecha, tipoDoc: tipoDoc, numDoc: numeroDoc, motivo: motivo, ingreso: cantidad };
        if (tipoDoc === "Factura") {
          RegistrarService.createMovimiento("salida", movimientoFactura);
        } else {
          RegistrarService.createMovimiento("entrada", movimientoBoleta);
        }
      } else {
        swal("No se ha registrado el usuario", { icon: "error", timer: "3000" });
      }
    });
  }


  return (
    <RegistrarStyle>
      <GlobalStyle />
      <div className="center-text">
        {"Cash Balance".split("").map((char, index) => (
          <span key={index}>{char}</span>
        ))}
        <img src={Logo2} alt="Logo2" className="logo" style={{ width: "80px", height: "auto", marginLeft: "5px", verticalAlign: "top", marginTop: "15px" }} />
      </div>
      <div className="movimientoForm-main-envoltura">
        <button className="return-button" onClick={() => window.location.href = '/'}>Volver al menú principal</button>
        <div className="movimientoForm-form-envoltura">
          <h1>Registrar movimiento</h1>
          <form onSubmit={saveMovimiento}>
            <div className="flex flex-wrap movimientoForm--mx-3">
              <div className="ancho movimientoForm-px-3 tipo-documento" >
                <label htmlFor="date" className="movimientoForm-form-label">Fecha del movimiento</label>
                <input type="date" name="date" id="date" className="movimientoForm-form-input" value={fecha} onChange={changeFechaHandler} />
              </div>
              <div className="tipo-documento">
                <label className="movimientoForm-form-label" >Tipo de documento</label>
                <div className="flex movimientoForm-mb-5-fecha">
                  <div className="flex">
                    <input type="radio" name="documentType" value="Boleta" id="radioButton1" className="movimientoForm-radio" onChange={changeTipoDocHandler} />
                    <label htmlFor="radioButton1" className="movimientoForm-radio-label">Boleta</label>
                  </div>
                  <div className="flex">
                    <input type="radio" name="documentType" value="Factura" id="radioButton2" className="movimientoForm-radio" onChange={changeTipoDocHandler} />
                    <label htmlFor="radioButton2" className="movimientoForm-radio-label">Factura</label>
                  </div>
                </div>
              </div>
              <div className="ancho sm:w-mitad movimientoForm-px-3">
                <div className="movimientoForm-mb-5">
                  <label htmlFor="documentNumber" className="movimientoForm-form-label">Número de documento</label>
                  <input type="text" name="documentNumber" id="documentNumber" placeholder="Id de documento" className="movimientoForm-form-input" value={numeroDoc} onChange={changeNumDocHandler} />
                </div>
              </div>
              <div className="ancho sm:w-mitad movimientoForm-px-3-cantidad">
                <div className="movimientoForm-mb-5">
                  <label htmlFor="quantity" className="movimientoForm-form-label">Cantidad</label>
                  <input type="text" name="quantity" id="quantity" placeholder="$" className="movimientoForm-form-input" value={cantidad} onChange={changeCantidadHandler} />
                </div>
              </div>
            </div>
            <div className="movimientoForm-mb-5">
              <label htmlFor="reason" className="movimientoForm-form-label">Motivo del movimiento</label>
              <textarea name="reason" id="reason" placeholder="Describa el motivo aquí..." className="movimientoForm-form-input" rows="4" maxLength={255} value={motivo} onChange={changeMotivoHandler}></textarea>
            </div>
            <div>
              <button className="movimientoForm-btn">Enviar Movimiento</button>
            </div>
          </form>
        </div>
      </div>
    </RegistrarStyle>
  );
};

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

const RegistrarStyle = styled.div`
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  .center-text {
    text-align: left; 
    font-size: 80px;
    color: white;
    -webkit-text-stroke: 2px white;
    position: relative;
    overflow: hidden;
    padding: 8px; 
  }

  body {
    font-family: "Inter", sans-serif;
  }
  .movimientoForm-mb-5,
  .movimientoForm-my-5-fecha {
    margin-bottom: 20px;
  }
  .movimientoForm-mb-5-fecha {
    margin-left: 30px;
  }

  .movimientoForm-main-envoltura {
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 48px;
    padding-top: 120px;
  }
  .movimientoForm-form-envoltura {
    margin: 0 auto;
    max-width: 550px;
    width: 100%;
    background: white;
    border-radius: 10px;
    border: 4px solid #E84393;
    padding: 20px;
    box-shadow: 0 0 10px rgba(255,105,180,0.3);

    @media (max-width: 600px) {
      max-width: 100%;
      padding: 10px;
    }
  }
  h1 {
    text-align: center;
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
  .movimientoForm-btn {
    text-align: center;
    font-size: 16px;
    border-radius: 6px;
    padding: 14px 32px;
    border: none;
    font-weight: 600;
    background-color: #6a64f1;
    color: white;
    cursor: pointer;
  }
  .movimientoForm-btn:hover {
    box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
  }
  .movimientoForm--mx-3 {
    margin-left: -12px;
    margin-right: -12px;
  }
  .movimientoForm-px-3,
  .movimientoForm-px-3-cantidad {
    padding-left: 12px;
    padding-right: 12px;
  }

  .movimientoForm-px-3-cantidad {
    margin-left: 128px
  }

  .flex {
    display: flex;
  }
  .flex-wrap {
    flex-wrap: wrap;
  }
  .ancho {
    width: 100%;
  }
  .movimientoForm-radio {
    width: 20px;
    height: 20px;
  }
  .movimientoForm-radio-label {
    font-weight: 500;
    font-size: 16px;
    padding-left: 12px;
    color: #070707;
    padding-right: 20px;
  }
  @media (min-width: 540px) {
    .sm\\:w-mitad {
      width: 50%;
    }
  }


  .tipo-documento {
    margin-bottom: 20px;
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
