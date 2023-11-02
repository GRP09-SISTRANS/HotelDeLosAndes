
from faker import Faker
import oracledb
import random


class DataGenerator:
    def __init__(self):
        # config database connection
        self.cur = None
        self.conn = None

        self.faker = Faker('en_US')
        # define sequences
        self.secuencia_usuarios = 1
        self.secuencia_servicios = 1
        self.secuencia_reservas = 1
        self.secuencia_reservas_servicios = 1
        self.secuencia_reservas_habitaciones = 1
        self.secuencia_habitaciones = 1
        self.secuencia_cuenta = 1
        self.secuencia_producto = 1
        self.secuencia_plan_consumo = 1000

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
        for i in range(cantidad_registros):
            servicio_general = {
                'id': self.secuencia_servicios,
                'nombre': self.faker.word() + ' Tienda',
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
<<<<<<< HEAD
                    'nombre': self.faker.word(),
=======
                    'nombre': self.faker.name(),
>>>>>>> 2ff0479b4b336ed96bd18d37b6029e064fd4fb53
                    'tipo': 'Salon'
                }
            servicio_salon = {'servicio_id': self.secuencia_servicios, 'capacidad': self.faker.random_int(min=1, max=100)}

            self.cur.execute("INSERT INTO servicio (id, nombre, tipo) VALUES (:id, :nombre, :tipo)", servicio_general)
            self.cur.execute("INSERT INTO salon_conferencia (servicio_id, capacidad) VALUES (:servicio_id, :capacidad)", servicio_salon)

            self.secuencia_servicios += 1
            self.conn.commit()

    def crearLavanderia(self, cantidad_registros:int):
        for i in range(cantidad_registros):
            servicio_general = {
                'id': self.secuencia_servicios,
                'nombre': self.faker.name(),
                'tipo': 'Lavanderia'
            }
            
            servicio_tienda = {'servicio_id': self.secuencia_servicios}
            
            self.cur.execute("INSERT INTO servicio (id, nombre, tipo) VALUES (:id, :nombre, :tipo)", servicio_general)
            self.cur.execute("INSERT INTO lavanderia (servicio_id) VALUES (:servicio_id)", servicio_tienda)

            self.secuencia_servicios += 1
            self.conn.commit()

    def crearPrestamoUtensilios(self, cantidad_registros: int): 
        for i in range(cantidad_registros):
            servicio_general = {
                'id': self.secuencia_servicios,
                'nombre' : self.faker.name(),
                'tipo': 'Prestamo'
            }

            servicio_tienda = {'servicio_id': self.secuencia_servicios}
            
            self.cur.execute("INSERT INTO servicio (id, nombre, tipo) VALUES (:id, :nombre, :tipo)", servicio_general)
            self.cur.execute("INSERT INTO prestamo_utensilios (servicio_id) VALUES (:servicio_id)", servicio_tienda)
            
            self.secuencia_servicios += 1
            self.conn.commit() 
             
    def crearSpa(self, cantidad_registros: int): 
        for i in range(cantidad_registros):
            nombre = self.faker.word() +" Spa"
            servicio_general = {
                'id': self.secuencia_servicios,
                'nombre' : nombre.capitalize(),
                'tipo': 'Spa'
            }

            servicio_tienda = {'servicio_id': self.secuencia_servicios}
            
            self.cur.execute("INSERT INTO servicio (id, nombre, tipo) VALUES (:id, :nombre, :tipo)", servicio_general)
            self.cur.execute("INSERT INTO Spa (servicio_id) VALUES (:servicio_id)", servicio_tienda)
            
            self.secuencia_servicios += 1
            self.conn.commit()
    
    def crearSupermercado(self, cantidad_registros: int): 
        for i in range(cantidad_registros):
            servicio_general = {
                'id': self.secuencia_servicios,
                'nombre' : self.faker.name(),
                'tipo': 'SuperMercado'
            }
            
            servicio_tienda = {'servicio_id': self.secuencia_servicios}
        

            self.cur.execute("INSERT INTO servicio (id, nombre, tipo) VALUES (:id, :nombre, :tipo)", servicio_general)
            self.cur.execute("INSERT INTO Supermercado (servicio_id) VALUES (:servicio_id)", servicio_tienda)
            
            self.secuencia_servicios += 1
            self.conn.commit()

    def crearHabitacion(self, cantidad_pisos: int):
        for i in range(cantidad_pisos+1):
            for j in range(1,10):
                tipo = 3
                capacidad = (j%3)+1
                id = i*100+j
                if j%9 == 0:
                    tipo = 1
                    capacidad = 4
                elif j%8 == 0 or j%7 == 0:
                    capacidad = 4
                    tipo = 2
                
                habitaciones_general = {
                    'id': id,
                    'capacidad': capacidad,
                    'tipo_habitacion_id': tipo
                }

                self.cur.execute("INSERT INTO Habitacion (id, capacidad, tipos_habitacion_id) VALUES (:id, :capacidad, :tipo_habitacion_id)", habitaciones_general)
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
        self.cur.execute("DELETE FROM reserva_servicio")
        self.cur.execute("DELETE FROM cuenta")
        self.cur.execute("DELETE FROM producto")
        self.cur.execute("DELETE FROM servicio")
<<<<<<< HEAD
        
        self.cur.execute("DELETE FROM usuario")
=======
        self.cur.execute("DELETE FROM habitacion")
>>>>>>> 2ff0479b4b336ed96bd18d37b6029e064fd4fb53

        self.conn.commit()
    
    def crearBar(self, cantidad_registros: int):

        for i in range(cantidad_registros):
            servicio_general = {
                    'id': self.secuencia_servicios,
                    'nombre': self.faker.word() + ' Bar',
                    'tipo': 'Bar'
                }
            servicio_bar = {'servicio_id': self.secuencia_servicios, 'capacidad': self.faker.random_int(min=1, max=100), 'estilo': self.faker.word()}

            self.cur.execute("INSERT INTO servicio (id, nombre, tipo) VALUES (:id, :nombre, :tipo)", servicio_general)
            self.cur.execute("INSERT INTO bar (servicio_id, capacidad, estilo) VALUES (:servicio_id, :capacidad, :estilo)", servicio_bar)

            self.secuencia_servicios += 1
            self.conn.commit()

    def crearPiscina(self, cantidad_registros: int):
        for i in range(cantidad_registros):
            servicio_general = {
                    'id': self.secuencia_servicios,
                    'nombre': self.faker.word() + ' Piscina',
                    'tipo': 'Piscina'
                }
            servicio_piscina = {'servicio_id': self.secuencia_servicios, 'capacidad': self.faker.random_int(min=1, max=100), 'profundidad': self.faker.random_int(min=1, max=100)}

            self.cur.execute("INSERT INTO servicio (id, nombre, tipo) VALUES (:id, :nombre, :tipo)", servicio_general)
            self.cur.execute("INSERT INTO piscina (servicio_id, capacidad, profundidad) VALUES (:servicio_id, :capacidad, :profundidad)", servicio_piscina)
            self.secuencia_servicios += 1
            self.conn.commit()
    
    def crearGimnasio(self, cantidad_registros: int):
        for i in range(cantidad_registros):
            servicio_general = {
                    'id': self.secuencia_servicios,
                    'nombre': self.faker.word() + ' Gym',
                    'tipo': 'Gimnasio'
                }
            servicio_gimnasio = {'servicio_id': self.secuencia_servicios, 'maquinas': self.faker.random_int(min=1, max=100)}

            self.cur.execute("INSERT INTO servicio (id, nombre, tipo) VALUES (:id, :nombre, :tipo)", servicio_general)
            self.cur.execute("INSERT INTO gimnasio (servicio_id, maquinas) VALUES (:servicio_id, :maquinas)", servicio_gimnasio)
            self.secuencia_servicios += 1
            self.conn.commit()
    
    def crearInternet(self, cantidad_registros: int):
        for i in range(cantidad_registros):
            servicio_general = {
                    'id': self.secuencia_servicios,
                    'nombre': self.faker.word() + ' Internet',
                    'tipo': 'Internet'
                }
            servicio_internet = {'servicio_id': self.secuencia_servicios}

            self.cur.execute("INSERT INTO servicio (id, nombre, tipo) VALUES (:id, :nombre, :tipo)", servicio_general)
            self.cur.execute("INSERT INTO internet (servicio_id) VALUES (:servicio_id)", servicio_internet)
            self.secuencia_servicios += 1
            self.conn.commit()
    
    def crearProducto(self, cantidad_registro:int):
        for i in range(cantidad_registro):
            producto = {
                'id': self.secuencia_producto,
                'servicio_id': self.faker.random_int(min=1, max=self.secuencia_servicios-1),
                'nombre': self.faker.word() + ' Producto',
                'costo': self.faker.random_int(min=1, max=100000)
            }
            self.cur.execute("INSERT INTO producto (id, servicio_id, nombre, costo) VALUES (:id, :servicio_id, :nombre, :costo)", producto)
            self.secuencia_producto += 1
            self.conn.commit()
    
    def crearUsuario(self, cantidadRegistro:int, tipo_usuario:str):
        tipos_documento = ['CC', 'CE', 'TI', 'PP']
        for i in range(cantidadRegistro):
            usuario = {
                'id': self.secuencia_usuarios,
                'tipo_doc': tipos_documento[random.randint(0,len(tipos_documento)-1)],
                'correo': self.faker.email(),
                'direccion': self.faker.address(),
                'nombre': self.faker.name(),
                'apellido': self.faker.last_name(),
                'contrasenia': '123456789',
                'tipo_usuario': tipo_usuario,
                'plan_consumo_id': self.faker.random_int(min=1, max=self.secuencia_plan_consumo-1)
            }
            self.cur.execute("INSERT INTO usuario (id, tipo_doc, correo, direccion, nombre, apellido, contrasenia, tipo_usuario, plan_consumo_id) VALUES (:id, :tipo_doc, :correo, :direccion, :nombre, :apellido, :contrasenia, :tipo_usuario, :plan_consumo_id)", usuario)
            self.secuencia_usuarios += 1
            self.conn.commit()
    
    def crearReservaServicio(self, cantidadRegistro:int):
        for i in range(cantidadRegistro):
           
            # Generar una fecha aleatoria
            fecha = self.faker.date_between(start_date='-2y', end_date='today')
            # Formatear la fecha para su inserción en Oracle
            fecha_oracle = fecha.strftime('%Y-%m-%d').upper()
            reserva_servicio = {
                'id': self.secuencia_reservas_servicios,
                'fecha': fecha_oracle, # 'DD-MON-YYYY
                'producto_id': self.faker.random_int(min=1, max=self.secuencia_producto-1),
                'usuario_id': self.faker.random_int(min=1, max=self.secuencia_usuarios-1)
            }
            self.cur.execute("INSERT INTO reserva_servicio (id, fecha, producto_id, usuario_id) VALUES (:id, to_date(:fecha, 'yyyy/mm/dd'), :producto_id, :usuario_id)", reserva_servicio)
            self.secuencia_reservas_servicios += 1
            self.conn.commit()
    
    def crearCuenta(self, cantidadRegistro:int):
        fecha = self.faker.date_between(start_date='-2y', end_date='today')
        for i in range(cantidadRegistro):
            cuenta = {
                'id': self.secuencia_cuenta,
                'reserva_id': self.faker.random_int(min=1, max=self.secuencia_usuarios-1),
                'producto_id': self.faker.random_int(min=1, max= self.secuencia_producto-1),
                'cantidad': self.faker.random_int(min=1, max=100),
                'fecha': fecha.strftime('%d-%b-%Y').upper(), # 'DD-MON-YYYY        
            }
            self.cur.execute("INSERT INTO cuenta (id, reserva_id, producto_id, cantidad, fecha) VALUES (:id, :reserva_id, :producto_id, :cantidad, :fecha)", cuenta)
            self.secuencia_cuenta += 1
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
<<<<<<< HEAD
    data_generator.limpiarBase()
    #data_generator.crearSalonConferencia(10)
    #data_generator.crearBar(10)
    #data_generator.crearPiscina(10)
    #data_generator.crearGimnasio(10)
    data_generator.crearInternet(10)
    data_generator.crearProducto(10)
    data_generator.crearUsuario(10, 1)
    data_generator.crearReservaServicio(10)

=======
    DataGenerator.limpiarBase(data_generator)
    DataGenerator.crearSpa(data_generator, 10)
    DataGenerator.crearHabitacion(data_generator, 10)
>>>>>>> 2ff0479b4b336ed96bd18d37b6029e064fd4fb53
