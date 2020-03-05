import requests
import json

headers = {'content-type': 'application/json'}
pyload = {'titulo': 'Duvida', 'mensagem': 'Projeto não compila', 'nomeCurso' : 'Spring Boot'}

requestPost = requests.post("http://localhost:8080/topicos",data=json.dumps(pyload), headers=headers)
print("Metodos POST: " + requestPost.text)

requestGet = requests.get("http://localhost:8080/topicos?page=0&size=3&sort=id,desc")
print("Metodos GET ID 3: " + requestGet.text)


requestPut = requests.put("http://localhost:8080/topicos/3",data=json.dumps({"titulo":"Novo Titulo", "mensagem":"Nova mensagem de teste"}), headers=headers)
print("Metodos PUT ID 3: " +requestPut.text)

requestGet = requests.get("http://localhost:8080/topicos?nomeCurso=Spring Boot&page=0&size=3&sort=id,desc")
print("Metodos no curso Spring Boot: " +requestGet.text)

requestDelete = requests.delete("http://localhost:8080/topicos/2", headers=headers)
print("Metodo Delete no ID 2")

requestGet = requests.get("http://localhost:8080/topicos")
print("Metodos GET em todos os topicos: " +requestGet.text)