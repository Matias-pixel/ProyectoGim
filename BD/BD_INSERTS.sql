-- INSERT DENTRO DE TIPO EQUIPAMIENTO
INSERT INTO tipoequipamiento VALUES (null, 'Maquinas'),(null, 'Aerobic'),(null, 'Cardio'),(null, 'Fuerza'),(null, 'Pesas'),(null, 'Pilates');

-- INSERT DENTRO DE EQUIPAMIENTO
                                    --  nombre        descripción        cantidaad    id_tipo_categoria
INSERT INTO EQUIPAMIENTO VALUES (null, 'Trotadora','Trotadora 100 kg max',  10    ,        3          ),
                                (null, 'Bicicleta','Bicicleta Spinning',   12    ,          3          ),
                                (null, 'Discos 15 kg','Discos para barras',   10    ,       5          ),
                                (null, 'Press Banca','Banca para press inclinada',   4    , 1          ),
                                (null, 'Mancuerna Peso Ajustable','Mancuerna 5 - 10 - 15 kg',   10   ,5),
                                (null, 'Trotadora','Trotadora 100 kg max',   20    ,        3          ),
                                (null, 'Barra para dominadas','Multifuncional',   4    ,    1          ),
                                (null, 'Banca Abdominal','Banca inclinada',       5    ,    1          ),
                                (null, 'Step Aeróbico','Permite coordinación y resistencia',10    , 2  ),
                                (null, 'Banda de resistencia','Banda de latex',              4    , 2  ),
                                (null, 'Colchoneta Yoga','Colchoneta blandita',              10   , 6  ),
                                (null, 'Balón Pilates  ','Balón Inflable pilates',           5    , 6  );


-- INSERT DENTRO DE TIPO USUARIO

INSERT INTO tipousuario VALUES (null, 'admin'),(null, 'Entrenador'),(null, 'No Vigente');

-- INSERT DENTRO DE USUARIO
                    -- ADMIN
                    INSERT INTO USUARIO VALUES (null, '111-1','Soy','Admin', SHA2('12345',0), 'AdminMax@gmail.com', 1);

                    -- Entrenadores

                    INSERT INTO USUARIO VALUES (null,'222-2','Pepe','Tapia', SHA2('123456',0),'PepeTapia@gmail.com',2);
                    INSERT INTO USUARIO VALUES (null,'333-3','Sebástian','Soto', SHA2('1234567',0),'SebaSoto@gmail.com',2);
                    INSERT INTO USUARIO VALUES (null,'444-4','Boris','FitnessMan', SHA2('1234568',0),'BorisFitness@gmail.com',2);


-- INSERT DENTRO DE TIPO ACTIVIDAD

INSERT INTO tipoactividad VALUES (null, 'Entrenamiento Funcional'),(null, 'Body Pump'),(null, 'Cardio'),(null, 'Yoga'),(null, 'Muscular');


-- INSERT DENTRO DE ACTIVIDAD
                                                                                   -- ID_EQUIP - ID TIPO ACTIV - ID USUARIO
INSERT INTO ACTIVIDAD VALUES (null,'Cardio Leve','Cardio en trotadora', 5 , '2020-11-29 18:30:00' , 1 , 3 , 2 ),
                             (null,'Trabajo Biceps','Trabajo biceps con mancuerna', 10 , '2020-12-03 15:30:00' , 5 , 5 , 3),
                             (null,'Yin Yoga ','Serie de Yoga tipo Yin', 10 , '2020-12-05 09:30:00' , 11 , 4 , 4 ),
                             (null,'Serie de Abdominales','Serie 5x30', 5 , '2020-12-05 18:30:00' , 8 , 1 , 1 ),
                             (null,'Serie de Dominadas','Serie 5x10', 4 , '2020-12-06 19:30:00' , 7 , 1 , 2 ),
                             (null,'Apertura Mancuernas','Serie 3 x 15 - 15 KG', 10 , '2020-12-15 15:30:00' , 5 , 5 , 3 ),
                             (null,'Spinning Indoor','Cardio Bicicletas estáticas', 12 , '2020-12-16 18:30:00' , 2 , 3 , 4 ),
                             (null,'Trabajo Triceps','Trabajo Biceps con mancuerna', 10 , '2020-12-20 12:30:00' , 5 , 5 , 2 );

