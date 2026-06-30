const persona = {
  nombre: "Victor Rafael",
  apellidos: "Machado Arteaga",
  edad: 62,
  libros: [
    { autor: "Pepito", titulo: "Habia una vez" },
    { autor: "Margatita", titulo: "La vida es bella" },
  ],
}
const personaJSON = JSON.stringify(persona)
console.log(personaJSON)
const persona2 = JSON.parse(personaJSON)
console.log(persona2.nombre)
console.log(persona2.apellidos)
