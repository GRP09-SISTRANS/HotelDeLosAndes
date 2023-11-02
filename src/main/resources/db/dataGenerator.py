
from faker import Faker
import oracledb


class DataGenerator:
    def __init__(self):
        # config database connection
        self.cur = None
        self.conn = None

        self.faker = Faker('es_ES')
        # define sequences
        self.secuencia_usuarios = 1
        self.secuencia_servicios = 1
        self.secuencia_reservas = 1
        self.secuencia_reservas_servicios = 1
        self.secuencia_reservas_habitaciones = 1
        self.secuencia_habitaciones = 1
        self.secuencia_cuenta = 1
        self.secuencia_producto = 1

    def create_connection(self, host: str, port: str, user: str, password: str):
        dsn_tns = oracledb.makedsn(host, port, service_name='PROD')
        connection = oracledb.connect(
            user=user,
            password=password,
            dsn=dsn_tns
        )
        self.conn = connection
        self.cur = connection.cursor()

    def crearTienda(self, cantidad_registros: int):
        for _ in range(cantidad_registros):
            # tuple with the data to insert
            servicio_general = {
                'id': self.secuencia_servicios,
                'nombre': self.faker.name(),
                'tipo': 'Tienda'
            }
            
            # tuple with the tienda data
            servicio_tienda = {'servicio_id': self.secuencia_servicios}
            
            # Inserta los valores en la tabla servicio del diccionario servicio_general
            self.cur.execute(
                "INSERT INTO servicio (id, nombre, tipo) VALUES (:id, :nombre, :tipo)", servicio_general
            )
            
            # Inserta los valores en la tabla tienda del diccionario servicio_tienda
            self.cur.execute("INSERT INTO tienda (servicio_id) VALUES (:servicio_id)", servicio_tienda)
            
            # Incrementa la secuencia de servicios a lo que ya se insertó un servicio
            self.secuencia_servicios += 1
            
            # Guarda los cambios en la base de datos
            self.conn.commit()
    
    def crearSalonConferencia(self, cantidad_registros:int):
        for i in range(cantidad_registros):
            servicio_general = {
                    'id': self.secuencia_servicios,
                    'nombre': Faker('es_ES').name(),
                    'tipo': 'Salon'
                }
            servicio_salon = {'servicio_id': self.secuencia_servicios, 'capacidad': self.faker.random_int(min=1, max=100)}

            self.cur.execute("INSERT INTO servicio (id, nombre, tipo) VALUES (:id, :nombre, :tipo)", servicio_general)
            self.cur.execute("INSERT INTO salon_conferencia (servicio_id, capacidad) VALUES (:servicio_id, :capacidad)", servicio_salon)

            self.secuencia_servicios += 1
            self.conn.commit()
    
    def limpiarBase(self):
        self.cur.execute("DELETE FROM bar")
        self.cur.execute("DELETE FROM piscina")
        self.cur.execute("DELETE FROM gimnasio")
        self.cur.execute("DELETE FROM salon_conferencia")
        self.cur.execute("DELETE FROM internet")
        self.cur.execute("DELETE FROM tienda")
        self.cur.execute("DELETE FROM lavanderia")
        self.cur.execute("DELETE FROM prestamo_utensilios")
        self.cur.execute("DELETE FROM spa")
        self.cur.execute("DELETE FROM supermercado")
        self.cur.execute("DELETE FROM producto")
        self.cur.execute("DELETE FROM servicio")

        self.conn.commit()


if __name__ == '__main__':
    # instance the DataGenerator class
    data_generator = DataGenerator()

    # config database connection
    data_generator.create_connection(
        host = 'fn4.oracle.virtual.uniandes.edu.co',
        port = '1521',
        user = 'ISIS2304D06202320',
        password= 'CfHmMdpRQPKQ'
    )
    DataGenerator.limpiarBase(data_generator)
    DataGenerator.crearSalonConferencia(data_generator, 100)
