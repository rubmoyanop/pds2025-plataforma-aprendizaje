[![Volver al inicio](https://img.shields.io/badge/⬅️_Volver_al_inicio-4CAF50?style=for-the-badge)](../../README.md)
[![Ver todos los CdU](https://img.shields.io/badge/📋_Ver_todos_los_CdU-C62828?style=for-the-badge)](./CasosDeUso.md)

# Caso de Uso Extendido: Guardar estadísticas de uso

*Actor principal:* Sistema.

*Personal involucrado e intereses:*

- Sistema: Almacenar la información correspondiene a las estadísticas del Estudiante para poder crear ejercicios personalizados, basados en su experiencia.
- Estudiante: Consultar las estadísticas de los cursos realizados para tener una visión general de su progreso.

*Precondiciones:* El Estudiante elige una estrategia de aprendizaje que modele los ejercicios que va a realizar. El Estudiante elige un curso de entre los disponibles.

*Garantías de éxito (Postcondiciones):* El Estudiante consulta sus estadísticas de uso de la aplicación.

*Flujo básico:*

1. El Estudiante inicia sesión en la aplicación.  
2. El Sistema actualiza el contador de minutos del Estudiante.  
3. El Estudiante selecciona un curso.  
4. El Sistema actualiza las estadísticas de éxito del Estudiante.  
5. El Sistema compara las estadísticas actualizadas con las guardadas.  
6. El Sistema actualiza los registros de racha correspondientes.  

   Se repiten los pasos 3-6 hasta que el Estudiante se desconecte.  

7. El Estudiante cierra sesión.  
8. El Sistema para el contador de minutos de la sesión y actualiza el contador general.  

*Flujos alternativos:*

- 3a. El Estudiante no realiza ningún curso. Solamente se actualiza el tiempo en aplicación.  

- 3b. El Estudiante consulta las estadísticas guardadas.  
    1. El Sistema actualiza las estadísticas generales con las estadísticas de sesión registradas hasta el momento.  
    2. El Sistema muestra las estadísticas al Estudiante.  

---

[![Volver al inicio](https://img.shields.io/badge/⬅️_Volver_al_inicio-4CAF50?style=for-the-badge)](../../README.md)
