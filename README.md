<div align="center">

<img src="src\main\resources\images\hispania_logo.png" alt="Hispania 360 Logo" width="250"/>

### Plataforma interactiva de aprendizaje sobre la Historia de España

[![Estado](https://img.shields.io/badge/Estado-En_Desarrollo-yellow.svg)](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje)
[![Java](https://img.shields.io/badge/Java-23-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.9.9-blue.svg)](https://maven.apache.org/)

</div>

---

## Índice

- [Índice](#índice)
- [FUNCIONALIDAD EXTRA](#funcionalidad-extra)
- [Descripción](#descripción)
- [Equipo de Desarrollo](#equipo-de-desarrollo)
- [Documentación](#documentación)
  - [Modelado y Diseño](#modelado-y-diseño)
  - [Casos de Uso](#casos-de-uso)
    - [Gestión de Usuarios](#gestión-de-usuarios)
    - [Gestión de Cursos](#gestión-de-cursos)
    - [Aprendizaje y Seguimiento](#aprendizaje-y-seguimiento)
  - [Referencias de Desarrollo](#referencias-de-desarrollo)
- [Estructura del Proyecto](#estructura-del-proyecto)
  - [Testing](#testing)
    - [Estructura de tests](#estructura-de-tests)
    - [Ejecutar los tests](#ejecutar-los-tests)
- [Ejecución del proyecto](#ejecución-del-proyecto)
  - [Compilar el proyecto](#compilar-el-proyecto)
  - [Ejecutar la aplicación](#ejecutar-la-aplicación)

## FUNCIONALIDAD EXTRA

Este proyecto incluye una funcionalidad adicional documentada en [`CasoUsoDarExperiencia.md`](./docs/casos%20de%20uso/CasoUsoDarExperiencia.md):  
**Dar experiencia** al estudiante tras completar un bloque de un curso.  
Cuando un estudiante finaliza un bloque, el sistema le otorga experiencia, actualiza sus estadísticas y le motiva a seguir aprendiendo.  
Esta funcionalidad fomenta la participación activa y el progreso continuo dentro de la plataforma.

## Descripción

<div align="center">
  
**Hispania 360** es una aplicación educativa diseñada para ofrecer cursos interactivos sobre la historia de España, combinando métodos de aprendizaje efectivos con una interfaz moderna y atractiva.

</div>

<details open>
<summary>Características principales</summary>

- 📚 **Cursos interactivos** sobre historia española
- 🧠 **Múltiples estrategias de aprendizaje** adaptadas al usuario
- 📊 **Seguimiento del progreso** y estadísticas personalizadas
- 👨‍🎓 **Perfiles diferenciados** para estudiantes y creadores de contenido
- ✨ **Interfaz moderna** y fácil de usar

</details>

## Equipo de Desarrollo

<table>
  <tr>
    <td align="center"><a href="https://github.com/teerueel"><img src="https://github.com/identicons/teerueel.png" width="100px;" alt=""/><br /><sub><b>Antonio Teruel Ruiz</b></sub></a></td>
    <td align="center"><a href="https://github.com/rubmoyanop"><img src="https://github.com/identicons/rubmoyanop.png" width="100px;" alt=""/><br /><sub><b>Rubén Moyano Palazón</b></sub></a></td>
    <td align="center"><a href="https://github.com/alexcerezal"><img src="https://github.com/identicons/alexcerezal.png" width="100px;" alt=""/><br /><sub><b>Alejandro Cerezal Jiménez</b></sub></a></td>
  </tr>
</table>

**Profesor:** Antonio López Martínez-Carrasco

## Documentación

### Modelado y Diseño

<a href="./docs/modelado/modelo_dominio.md">
  <img src="https://img.shields.io/badge/📊_Diagrama_de_Clases-Ver_Documentación-4CAF50?style=for-the-badge" alt="Diagrama de Clases"/>
</a>

### Casos de Uso

<div align="center">

#### Gestión de Usuarios

<div style="display: flex; flex-wrap: wrap; gap: 10px; justify-content: center; margin-bottom: 15px;">
  <a href="./docs/casos%20de%20uso/CasoUsoRegistrarEstudiante.md" style="text-decoration: none; margin: 5px;">
    <img src="https://img.shields.io/badge/👨‍🎓_Registrarse_(Estudiante)-C62828?style=for-the-badge&logoWidth=40" alt="Registrarse Estudiante"/>
  </a>
  <a href="./docs/casos%20de%20uso/CasoUsoRegistrarCreador.md" style="text-decoration: none; margin: 5px;">
    <img src="https://img.shields.io/badge/👨‍🏫_Registrarse_(Creador)-C62828?style=for-the-badge&logoWidth=40" alt="Registrarse Creador"/>
  </a>
</div>

#### Gestión de Cursos

<div style="display: flex; flex-wrap: wrap; gap: 10px; justify-content: center; margin-bottom: 15px;">
  <a href="./docs/casos%20de%20uso/CasoUsoElegirCurso.md" style="text-decoration: none; margin: 5px;">
    <img src="https://img.shields.io/badge/📋_Elegir_Curso-C62828?style=for-the-badge&logoWidth=40" alt="Elegir Curso"/>
  </a>
  <a href="./docs/casos%20de%20uso/CasoUsoImportarCurso.md" style="text-decoration: none; margin: 5px;">
    <img src="https://img.shields.io/badge/📥_Importar_Curso-C62828?style=for-the-badge&logoWidth=40" alt="Importar Curso"/>
  </a>
</div>

#### Aprendizaje y Seguimiento

<div style="display: flex; flex-wrap: wrap; gap: 10px; justify-content: center; margin-bottom: 15px;">
  <a href="./docs/casos%20de%20uso/CasoUsoDarExperiencia.md" style="text-decoration: none; margin: 5px;">
    <img src="https://img.shields.io/badge/⭐_Dar_Experiencia-C62828?style=for-the-badge&logoWidth=40" alt="Dar Experiencia"/>
  </a>
  <a href="./docs/casos%20de%20uso/CasoUsoElegirEstrategia.md" style="text-decoration: none; margin: 5px;">
    <img src="https://img.shields.io/badge/🧩_Elegir_Estrategia-C62828?style=for-the-badge&logoWidth=40" alt="Elegir Estrategia"/>
  </a>

  <a href="./docs/casos%20de%20uso/CasoUsoGuardarEstadisticas.md" style="text-decoration: none; margin: 5px;">
    <img src="https://img.shields.io/badge/📈_Guardar_Estadísticas-C62828?style=for-the-badge&logoWidth=40" alt="Guardar Estadísticas"/>
  </a>
</div>

<a href="./docs/casos%20de%20uso/CasosDeUso.md">
  <img src="https://img.shields.io/badge/Ver_todos_los_Casos_de_Uso-4CAF50?style=for-the-badge" alt="Ver todos los Casos de Uso"/>
</a>

</div>

### Referencias de Desarrollo

#### Issues Activos

| Issue | Estado | Descripción |
|-------|--------|-------------|
| [#13](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje/issues/13) | ✅ | Crear Vistas (Interfaz Gráfica) |
| [#19](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje/issues/19) | ✅ | Implementación de CdU: Registro Estudiante y Creador |
| [#20](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje/issues/20) | ✅ | Implementación de CdU: Elegir estrategia de aprendizaje |
| [#21](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje/issues/21) | ✅ | Implementación de CdU: Elegir un curso disponible |
| [#22](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje/issues/22) | ✅ | Implementación de CdU: Guardar estadísticas de uso |
| [#23](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje/issues/23) | ✅ | Implementación de CdU: Importar curso |
| [#24](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje/issues/24) | ✅ | Implementación de CdU: Guardar progreso del curso |
| [#26](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje/issues/26) | ✅ | Implementación de CdU: Dar Recompensa |
| [#31](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje/issues/31) | ✅ | Verificación de Campos en Formulario de Registro |
| [#32](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje/issues/32) | ✅ | Cambiar Caso de Uso adicional |
| [#34](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje/issues/34) | ✅ | Crear tests |
| [#43](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje/issues/43) | ✅ | Corrección entrega 2 |
| [#45](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje/issues/45) | ✅ | Persistencia |
| [#51](https://github.com/rubmoyanop/pds2025-plataforma-aprendizaje/issues/51) | ✅ | Revisar implementación estrategias |

<details>
<summary><b>Leyenda de Símbolos</b></summary>

- ✅ **Completado**: El issue ha sido finalizado y cerrado.
- 🚀 **En Progreso (Avanzado)**: La implementación está casi completada y en fase de refinamiento.
- 📄 **Propuesta Documentada**: La implementación ha sido propuesta y está en fase de revisión o documentación.

</details>

## Estructura del Proyecto

```bash
📁 pds2025-plataforma-aprendizaje/
├── 📁 docs/               # Documentación del proyecto
│   ├── 📁 casos de uso/   # Casos de uso detallados en Markdown
│   └── 📁 modelado/       # Diagramas y documentación del dominio
├── 📁 src/                # Código fuente
│   ├── 📁 main/           # Código principal
│   │   ├── 📁 java/       # Archivos Java organizados en paquetes
│   │   │   └── 📁 pds/hispania360/
│   │   │       ├── 📁 controlador/    # Controladores de la aplicación
│   │   │       ├── 📁 factoria/       # Factoría para crear objetos
│   │   │       ├── 📁 modelo/         # Modelo de datos y lógica de negocio
│   │   │       │   └── 📁 ejercicios/ # Tipos de ejercicios
│   │   │       ├── 📁 persistencia/   # Adaptadores de persistencia (SQLite, etc)
│   │   │       ├── 📁 repositorio/    # Interfaces de repositorio
│   │   │       ├── 📁 sesion/         # Gestión de sesiones
│   │   │       ├── 📁 util/           # Utilidades varias
│   │   │       └── 📁 vista/          # Componentes de la interfaz gráfica
│   │   └── 📁 resources/  # Recursos (imágenes, archivos de configuración)
│   └── 📁 test/           # Código de pruebas
├── 📁 target/             # Archivos generados por Maven
├── 📄 pom.xml             # Configuración de Maven
└── 📄 README.md           # Este archivo
```

### Testing

<div align="center">
<img src="https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white" alt="JUnit 5"/>
<img src="https://img.shields.io/badge/AssertJ-43853D?style=for-the-badge" alt="AssertJ"/>
</div>

El proyecto cuenta con pruebas unitarias que verifican el correcto funcionamiento de los componentes principales. Actualmente el proyecto tiene estos tests:

#### Estructura de tests

```bash
📁 src/test/java/pds/hispania360/
├── 📄 AppTest.java                     # Tests básicos de la aplicación
├── 📁 controlador/                     # Tests de la capa de controlador
│   └── 📄 ControladorTest.java         # Tests de inicio/cierre sesión e importación
├── 📁 factoria/                        # Tests de factoría
│   └── 📄 FactoriaEjercicioTest.java   # Tests de creación de ejercicios
├── 📁 modelo/                          # Tests de modelo de datos
│   ├── 📄 CursoTest.java               # Tests de la clase Curso
│   └── 📄 UserTest.java                # Tests de la clase Usuario
├── 📁 persistencia/                    # Tests de adaptadores de persistencia
│   ├── 📄 RepositorioCursoPersistenteIT.java         # Tests de persistencia de cursos
│   ├── 📄 RepositorioProgresoCursoPersistenteIT.java # Tests de persistencia de progreso de curso
│   └── 📄 RepositorioUsuarioPersistenteIT.java       # Tests de persistencia de usuarios
├── 📁 repositorio/                     # Tests de interfaces de repositorio├── 📁 persistencia/                    # Tests de adaptadores de persistencia
│   ├── 📄 RepositorioCursoPersistenteIT.java         # Tests de persistencia de cursos
│   ├── 📄 RepositorioProgresoCursoPersistenteIT.java # Tests de persistencia de progreso de curso
│   └── 📄 RepositorioUsuarioPersistenteIT.java       # Tests de persistencia de usuarios
├── 📁 repositorio/                     # Tests de interfaces de repositorio
│   ├── 📄 RepositorioCursoTest.java    # Tests del repositorio de cursos
│   └── 📄 RepositorioUsuarioTest.java  # Tests del repositorio de usuarios
└── 📁 sesion/
    └── 📄 SesionTest.java              # Tests de gestión de sesiones
```

#### Ejecutar los tests

Ejecutar toda la suite de tests:

```bash
mvn test
```

Ejecutar una clase de test específica:

```bash
mvn test -Dtest=ControladorTest
```

Ejecutar un método de test específico:

```bash
mvn test -Dtest=RepositorioCursoPersistenteIT#testObtenerCursoPorId
```

## Ejecución del proyecto

<div align="center">
<img src="https://img.shields.io/badge/Maven-Requerido-1565C0?style=for-the-badge&logo=apache-maven" alt="Maven Requerido"/>
</div>

<details open>
<summary><b>Requisitos previos</b></summary>
<br/>

- [Java JDK 23](https://www.oracle.com/java/technologies/downloads/) o superior
- [Maven](https://maven.apache.org/install.html) 3.8.1 o superior
- Conexión a internet (para la primera compilación)

</details>

### Compilar el proyecto

```bash
mvn clean compile
```

### Ejecutar la aplicación

```bash
mvn exec:java
```

También puedes crear un .jar ejecutable y luego ejecutarlo:

```bash
mvn package
java -jar target/pds2025-plataforma-aprendizaje-1.0-SNAPSHOT.jar
```

---

<div align="center">
<i>Proyecto desarrollado para la asignatura de Procesos de Desarrollo de Software</i>
</div>
