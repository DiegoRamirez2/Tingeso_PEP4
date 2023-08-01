import os

def ejecutar_comandos_en_carpeta(carpeta, ruta_raiz):
    # Cambiar al directorio de la carpeta del microservicio
    ruta_microservicio = os.path.join(ruta_raiz, carpeta)
    os.chdir(ruta_microservicio)

    # Verificar si la imagen ya existe
    comando_verificar = "docker images -q ijynx1/" + carpeta
    output = os.popen(comando_verificar).read().strip()

    
    # Ejecutar los comandos de construcción y envío
    comando_build = "docker build -t ijynx1/" + carpeta + " ."
    os.system(comando_build)

    comando_push = "docker push ijynx1/" + carpeta
    os.system(comando_push)

def main():
    ruta_raiz = os.getcwd()
    # Nombre de las carpetas de los microservicios
    microservicios = ["config-service", "gateway-service", "entrada-service",
                        "salida-service", "movimientos-service"]

    # Ejecutar comandos en cada microservicio
    for microservicio in microservicios:
        ejecutar_comandos_en_carpeta(microservicio, ruta_raiz)

if __name__ == "__main__":
    main()
