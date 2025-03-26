# Caso de Uso Extendido: Registro de un Creador

## Actores

- **Creador:** Quiere poder registrarse en la plataforma identificándose con sus credenciales. 

## Precondiciones

- El Creador debe haber iniciado la aplicación.
- El Creador debe poseer un ID de Creador.

## Flujo principal

1. El Creador accede a la opción "Registrarse como Creador" en la pantalla principal.
2. El Sistema solicita un nombre, un correo electrónico, una contraseña y un ID de Creador.
3. El Creador introduce los datos requeridos y envía el formulario.
4. El Sistema valida el correo, la contraseña y el ID de Creador.
5. El Sistema crea el registro del estudiante en la base de datos.
6. El Sistema informa al Creador de su correcto registro. 


## Flujos alternativos

- **El correo está ya registrado:**
  5. El Sistema informa al Creador de que el correo ya está registrado.
  6. El Sistema vuelve al paso 2.

- **La contraseña no es válida:**
  4. El Sistema informa al Creador de que su contraseña no es válida.
  6. El Sistema vuelve al paso 2.

- **EL ID de Creador no es válido:**
  4. El Sistema informa al Creador de que su ID de Creador no es válido.
  6. El Sistema vuelve al paso 2.

## Postcondiciones

- El Creador está registrado.
- **(Poner cosas dependiendo de lo que mañana nos diga el profesor sobre Iniciar Sesión)**