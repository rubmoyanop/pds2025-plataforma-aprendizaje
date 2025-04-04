[![Volver al inicio](https://img.shields.io/badge/⬅️_Volver_al_inicio-4CAF50?style=for-the-badge)](../../README.md)
[![Ver todos los CdU](https://img.shields.io/badge/📋_Ver_todos_los_CdU-C62828?style=for-the-badge)](./CasosDeUso.md)

# Caso de Uso Extendido: Registro de un Estudiante

## Actores

- **Estudiante:** Quiere poder registrarse en la plataforma identificándose con sus credenciales. 

## Precondiciones

- El Estudiante debe haber iniciado la aplicación.

## Flujo principal

1. El Estudiante accede a la opción "Registrarse como Estudiante" en la pantalla de Registros.
2. El Sistema solicita un nombre, un correo electrónico y una contraseña.
3. El Estudiante introduce los datos requeridos y envía el formulario.
4. El Sistema valida el correo y la contraseña.
5. El Sistema crea el registro del estudiante en la base de datos.
6. El Sistema informa al Estudiante de su correcto registro. 
7. El Sistema redirige al Estudiante a la pantalla principal. 


## Flujos alternativos

- **El correo está ya registrado:**
  5. El Sistema informa al Estudiante de que el correo ya está registrado.
  6. El Sistema vuelve al paso 2.

- **La contraseña no es válida:**
  4. El Sistema informa al Estudiante de que su contraseña no es válida.
  6. El Sistema vuelve al paso 2.

## Postcondiciones

- El Estudiante está registrado.

---

[![Volver al inicio](https://img.shields.io/badge/⬅️_Volver_al_inicio-4CAF50?style=for-the-badge)](../../README.md)
