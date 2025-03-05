<div align="center">

# Hispania 360

## Plataforma interactiva de aprendizaje sobre la Historia de España

</div>

---

## 📝 Descripción

**Hispania 360** es una aplicación educativa diseñada para ofrecer cursos interactivos sobre la historia de España.

## 👥 Equipo de Desarrollo

* Antonio Teruel Ruiz - GitHub: @teerueel
* Rubén Moyano Palazón - GitHub: @rubmoyanop
* Alejandro Cerezal Jiménez - GitHub: @alexcerezal

**Profesor:** Antonio López Martínez-Carrasco

## 📓 Documentación

### Modelado y Diseño

* [📊 Diagrama de Clases](./docs/modelado/modelo_dominio.md)

### Casos de Uso

* [📝 Listado Casos de Uso](./docs/casos%20de%20uso/CasosDeUso.md)
* [📝 Caso de Uso 1: Elegir Curso](./docs/casos%20de%20uso/CasoUsoElegirCurso.md)
* [📝 Caso de Uso 2: Nivel Inicial](./docs/casos%20de%20uso/CasoUsoNivelInicial.md)

## 🚀 Ejecución del proyecto

Para ejecutar el proyecto, es necesario tener [Maven](https://maven.apache.org/install.html) instalado en tu sistema.

### Compilar el proyecto

```bash
mvn clean compile
```

### Ejecutar tests

```bash
mvn test
```

### Ejecutar la aplicación

```bash
mvn exec:java
```

También puedes crear un .jar ejecutable y luego ejecutarlo:

```bash
mvn package
java -jar target/pds2025-plataforma-aprendizaje-x.0-SNAPSHOT.jar

```

Proyecto desarrollado para la asignatura de Procesos de Desarrollo de Software
