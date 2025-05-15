[![Volver al inicio](https://img.shields.io/badge/⬅️_Volver_al_inicio-4CAF50?style=for-the-badge)](../../README.md)
[![Ver todos los CdU](https://img.shields.io/badge/📋_Ver_todos_los_CdU-C62828?style=for-the-badge)](./CasosDeUso.md)

# Caso de Uso Extendido: Importar Curso

## Actores

- **Creador:** Principal. Importa un curso creado por él para que otros usuarios puedan realizarlo.  
- **Sistema:** Comprueba que el curso tiene el formato esperado y lo sube a la aplicación.

## Precondiciones

- El Creador debe haber iniciado sesión en la aplicación como **rol Creador**.  
- El Creador debe haber creado un curso en un fichero JSON o YAML.

## Flujo principal

1. El Creador se registra en la aplicación.  
2. El Sistema muestra una pantalla de inicio.  
3. El Creador selecciona ver los cursos disponibles.  
4. El Sistema muestra dichos cursos y un botón para importar un nuevo curso.  
5. El Creador pulsa el botón de importar un nuevo curso.  
6. El Sistema abre un explorador de archivos donde se puede seleccionar el fichero JSON o YAML.  
7. El Creador selecciona el fichero a importar.  
8. El Sistema traduce el archivo a un curso.  
9. El Sistema actualiza sus cursos disponibles con el nuevo curso añadido.  
10. El Sistema refresca la ventana de cursos para mostrar el nuevo curso.

## Flujos alternativos

- **0A Usuario no tiene rol Creador**  
  1. El Sistema informa “Solo los usuarios creadores pueden importar cursos.”

- **7A Usuario cancela la selección de fichero**  
  1. El Sistema permanece en la ventana de cursos sin realizar ninguna acción.

- **8A El archivo no cumple con el formato necesario para crear un curso**  
  1. El Sistema informa al Creador de que ha habido un error con la importación.

- **8B Error al parsear o validar el fichero (JSON/YAML mal formado, metadatos incompletos, bloques sin ejercicios, etc.)**  
  1. El Sistema muestra un diálogo con el mensaje de error devuelto por el importador.

## Postcondiciones

- La aplicación dispone de un nuevo curso que los Estudiantes pueden realizar.  
- La ventana de cursos se ha refrescado mostrando el curso recién importado.

[![Volver al inicio](https://img.shields.io/badge/⬅️_Volver_al_inicio-4CAF50?style=for-the-badge)](../../README.md)