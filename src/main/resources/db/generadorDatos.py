import random
class GeneradorDatos:
    def __init__(self):
        self.sequencia_id = 0
    
    def generate_insert(self,table_name, num_rows:int, columnas):
        columns = ["column1", "column2", "column3"] # replace with actual column names
        values = []
        for i in range(num_rows):
            row_values = []
            for llave in columns:
                if llave == "column1":
                    row_values.append(str(i+1))
                else:
                    row_values.append(str(random.randint(1, 100)))
            values.append("(" + ", ".join(row_values) + ")")
        return "INSERT INTO {} ({}) VALUES {};".format(table_name, ", ".join(columns), ", ".join(values))

    def generar_valores(self,llave,columnas,table_name):
        posibles_servicios = ["'Spa'", "'Bar'", "'Prestamo'", "'Salon'", "'Tienda'", "'Gimnasio'", "'Internet'", "'SuperMercado'", "'Piscina'", "'Lavanderia'"]
        if llave == "tipo" and table_name == "servicios":
            return posibles_servicios[random.randint(0, len(posibles_servicios)-1)]
        if "id" in llave:
            return self.sequencia_id + 1
        elif columnas[llave] == "varchar2":
            return "'{}'".format("".join([chr(random.randint(97, 122)) for _ in range(random.randint(1, 7))]))
        elif columnas[llave] == "number":
            return str(random.randint(1, 100))
        elif columnas[llave] == "date":
            return "TO_DATE('{}-{}-{}', 'YYYY-MM-DD')".format(random.randint(1990, 2020), random.randint(1, 12), random.randint(1, 28))
        elif columnas[llave] == "char":
            return "'{}'".format(chr(random.randint(97, 122)))
        

caso_prueba = GeneradorDatos()
print (caso_prueba.generate_insert("tabla1", 10, ["column1", "column2", "column3"]))

print("'spa'")