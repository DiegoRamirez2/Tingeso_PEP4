import React from "react";
import styled from "styled-components";
import { createGlobalStyle } from "styled-components";
import Imagen from "../resources/imagen.jpg";
import bgImage from "../resources/fondo.jpg";
import Logo2 from "../resources/logo2.png";


export default function Home() {
  const redirigir = (url) => {
    window.location.href = url;
  }


  return (
    <div>
      <GlobalStyle />
      <HomeStyle>
        <div className="center-text">
          {"Cash Balance".split("").map((char, index) => (
            <span style={{ animationDelay: `${index * 0.1}s` }} key={index}>{char}</span>
          ))}
          <img src={Logo2} alt="Logo2" className="logo" style={{ width: "80px", height: "auto", marginLeft: "5px", verticalAlign: "top", marginTop: "15px" }} />
        </div>
        <div className="flexbox-container">
          <div className="background-image-container">
            <div className="question-box">
              <div className="header-text"><strong>¿Quieres llevar la contabilidad de tu negocio?</strong>
            <div className="text-box">Lleva la contabilidad de tu negocio a otro nivel con nuestra página web. Organiza y analiza  tus finanzas de manera sencilla y eficaz.</div>
              </div>
            </div>
              <img src={Imagen} alt="BB Logo" className="round-image" />
          </div>
          <div className="box-area">
            <div className="resumen">
              <div className="header-text"><strong>Registrar movimiento de dinero</strong>
                <div className="text-box">Registra y sigue de cerca cada movimiento financiero en tu negocio, manteniendo todo organizado y accesible. Ideal para tener un control eficiente de tus finanzas.💼</div>
                <button type="button" className="myButton" onClick={() => redirigir("/registrar")}>Registrar movimiento</button>
              </div>
            </div>
            <div className="subir-data">
              <div className="header-text"><strong>Ver resumen de movimientos </strong>
                <div className="text-box">Obtén un resumen detallado de tus movimientos económicos. Visualiza gráficos, tablas y toda la información detallada de tus movimientos.📊</div>
                <button type="button" className="myButton" onClick={() => redirigir("/resumen")}>Ver resumen</button>
              </div>
            </div>
          </div>
        </div>
      </HomeStyle>
    </div>
  )
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

const HomeStyle = styled.nav`
.center-text {
    text-align: center;
    justify-content: center;
    padding-top: 8px;
    font-size: 80px; 
    color: transparent;
    -webkit-text-stroke: 2px white; 
    position: relative;
    overflow: hidden;
  }
  
  .center-text span {
    display: inline-block; 
    transform: translateY(100%); 
    -webkit-text-fill-color: transparent; 
    animation: slide-up-fill 1s ease-out forwards;
  }
  
.round-image {
    border-radius: 2%; 
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); 
  }
  

  .logo {
    display: inline-block; 
    transform: translateY(100%); 
    animation: slide-up-logo 1.5s ease-out forwards;
  }
  

  .background-image-container {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column; 
    flex: 1;
    order: -1;
    position: relative;
    height: 605px;
  }
  
  .background-image-container img {
    width: 100%; 
    height: auto; 
    object-fit: cover;
  }
  

.box-area {
  display: flex;
  flex-wrap: wrap;
  align-items: flex-end;
  flex-direction: row; 
  justify-content: flex-end; 
  margin: 0 auto;
}

.flexbox-container {
  display: -ms-flex;
  display: -webkit-flex;
  display: flex;
  margin-top: 50px;
  animation: fade-in 2s ease-in-out; 
}

.flexbox-container > div {
  width: 50%;
  padding: 30;
  margin: 30px;
  border-radius: 10px;
}

.flexbox-container > div:first-child {
  margin-right: 30px;
}

.content-area {
  flex: 2;
  display: flex;
  flex-direction: column;
}

.resumen,
.subir-data,
.ayuda,
.question-box {
  width: fit-content; 
  border-radius: 15px;
  background-color: #ffffff;
  text-align: center;
  margin: 15px;
  padding: 15px;
  transition: .3s;
  margin-top: 1px;
  margin-right: 20px;
  display: flex;
}

.question-box {
  border: 4px solid #E84393;
}

.ayuda {
  border: 4px solid #46B63A;
}
.resumen {
  border: 4px solid #46B63A;
}
.subir-data {
  border: 4px solid #E84393;
}


.text-box{
  font-size: 24px;
  line-height: 24px;
}

.header-text{
  font-size: 24px;
  font-weight: 500;
  line-height: 48px;
}

.single-box:hover{
  background: #e84393;
  color: #fff;
}


.myButton {
	background-color:#44c767;
	border-radius:28px;
	border:1px solid #18ab29;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:15px;
	padding: 12px 15px;
	text-decoration:none;
	text-shadow:0px 1px 0px #2f6627;
}
.myButton:hover {
	background-color:#5cbf2a;
}
.myButton:active {
	position:relative;
	top:1px;
}

// Keyframe
@keyframes slide-up-logo {
  0% {
    transform: translateY(100%);
  }
  100% {
    transform: translateY(0);
  }
}

@keyframes fade-in {
0% {
  opacity: 0;
}
100% {
  opacity: 1;
}

}
@keyframes slide-up-fill {
0% {
  transform: translateY(100%);
  -webkit-text-fill-color: transparent;
}
50% {
  transform: translateY(0);
  -webkit-text-fill-color: transparent;
}
100% {
  transform: translateY(0);
  -webkit-text-fill-color: white; 
}
}


`;
