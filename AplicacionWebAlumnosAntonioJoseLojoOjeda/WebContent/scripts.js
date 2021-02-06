/**
 * Con este código se hacen las peticiones con AJAX a la parte servidor (Java Resources)
 */
//Cuando el documento esté cargado haz lo siguiente
$(document).ready(function(){
	

	//Alumnos
	
	/**
	 * Inserta alumno a partir de los elementos introducido en los checkBoxes
	 */
	$("#insertar").click(function(){
		$("#textarea").val("");
		dni =  $("#dni").val();
		nombre = $("#nombre").val();
		edad = $("#edad").val();
		//si hay datos
		if(dni != "" && nombre != "" && edad != ""){
				//Ajax --> enviar a servler insertar
			$.ajax({
				type: "POST", //Tipo de envío
				dataType: "html", //lo que voy a recibir
				url: "./ServletInsertar", //Servlet de unión
				data: $.param({ //parametros de envio los cojo a paritr de la id
					dni, 
					nombre,
					edad
				}),//si tiene exito
				success: function(result){
					//Deja los campos vacíos
					$("#textarea").val(result);
					
				},//si hay un error
				error: function(error){
					alert("Error:\nAlgo ha ido mal durante la insercción");
					$("#textarea").val("");
				}
			});
		}else{
			//si faltan datos muestra error
			alert("Error:\nDebes rellenar todos los campos")
		}
		//siempre deja los campos vacíos
		$("#dni").val("");
		$("#nombre").val("");
		$("#edad").val("");
		
		
		
		
	});
	
	
	// Elimina (solo hace falta el dni)
	$("#eliminar").click(function(){
		$("#textarea").val("");
		dni =  $("#dni").val();
		if(dni != ""){
				
			//Ajax --> enviar a servler eliminar
			$.ajax({
				type: "POST", //Tipo de envío
				dataType: "html", //Tipo de archivo
				url: "./ServletEliminar", //Servlet de unión
				data: $.param({ //parametros de envio OBVIAMENTE ESTO HABRÁ QUE HACERLO COGIENDO EL VALOR DE UN FORM
					dni
				}),
				success: function(result){
					$("#textarea").val(result);
				},
				error: function(error){
					alert("Error:\nAlgo ha ido mal durante el borrado");
					$("#textarea").val("");
				}
			});	
		}else{
			alert("Error:\nAl menos debes rellenar el campo para el DNI");
		}
		//siempre deja los campos vacíos
		$("#dni").val("");
		$("#nombre").val("");
		$("#edad").val("");
		
		
	});
	
	
	$("#consultar").click(function(){
		
		//Ajax --> enviar a consultar
		$.ajax({
			type: "POST", //Tipo de envío
			dataType: "html", //Tipo de archivo
			url: "./ServletConsultar", //Servlet de unión
			data: $.param({ //parametros de envio OBVIAMENTE ESTO HABRÁ QUE HACERLO COGIENDO EL VALOR DE UN FORM
				
			}),
			success: function(result){
				$("#textarea").val(result);
			},
			error: function(error){
				alert("Error:\nAlgo ha ido mal durante la consulta");
				$("#textarea").val("");
			}
		});
		$("#dni").val("");
		$("#nombre").val("");
		$("#edad").val("");
	
	});
	
	
	//Modifica -->
	$("#modificar").click(function(){
		dni =  $("#dni").val();
		nombre = $("#nombre").val();
		edad = $("#edad").val();
		//si hay datos
		if(dni != "" && nombre != "" && edad != ""){
			//Ajax --> enviar a servler modificar
			$.ajax({
				type: "POST", //Tipo de envío
				dataType: "html", //Tipo de archivo
				url: "./ServletModificar", //Servlet de unión
				data: $.param({ //parametros de envio OBVIAMENTE ESTO HABRÁ QUE HACERLO COGIENDO EL VALOR DE UN FORM
					dni,
					nombre,
					edad
				}),
				success: function(result){
					$("#textarea").val(result);
				},
				error: function(error){
					alert("Error:\nAlgo ha ido mal durante la modificación");
					$("#textarea").val("");
				}
			});
		}else{
			//si faltan datos muestra error
			alert("Error:\nDebes rellenar todos los campos")
		}
		$("#dni").val("");
		$("#nombre").val("");
		$("#edad").val("");
	});

	/**
	 * Asignaturas de un alumno
	 */
	$("#asig_alumno").click(function(){
		dni =  $("#dni").val();
		//si hay datos
		if(dni != "" ){
			//Ajax --> enviar a servler modificar
			$.ajax({
				type: "POST", //Tipo de envío
				dataType: "html", //Tipo de archivo
				url: "./ServletAsigAlumno", //Servlet de unión
				data: $.param({ //parametros de envio OBVIAMENTE ESTO HABRÁ QUE HACERLO COGIENDO EL VALOR DE UN FORM
					dni
				}),
				success: function(result){
					$("#textarea").val(result);
				},
				error: function(error){
					alert("Error:\nAlgo ha ido mal durante la modificación");
					$("#textarea").val("");
				}
			});
		}else{
			//si faltan datos muestra error
			alert("Error:\nDebes rellenar al menos el dni del alumno")
		}
		$("#dni").val("");
		$("#nombre").val("");
		$("#edad").val("");
	});





	//Asingaturas
	/**
	 * Crea una nueva asignatura
	 */
	$("#insertar_asig").click(function(){
		codigo_asig =  $("#codigo_asig").val();
		nombre_asig = $("#nombre_asig").val();
		creditos = $("#creditos").val();
		//si hay datos
		if(codigo_asig != "" && nombre_asig != "" && creditos != ""){
			//Ajax --> enviar a servler modificar
			$.ajax({
				type: "POST", //Tipo de envío
				dataType: "html", //Tipo de archivo
				url: "./ServletInsAsig", //Servlet de unión
				data: $.param({ //parametros de envio OBVIAMENTE ESTO HABRÁ QUE HACERLO COGIENDO EL VALOR DE UN FORM
					codigo_asig,
					nombre_asig,
					creditos
				}),
				success: function(result){
					$("#textarea").val(result);
				},
				error: function(error){
					alert("Error:\nAlgo ha ido mal durante la modificación");
					$("#textarea").val("");
				}
			});
		}else{
			//si faltan datos muestra error
			alert("Error:\nDebes rellenar todos los campos")
		}
		$("#codigo_asig").val("");
		$("#nombre_asig").val("");
		$("#creditos").val("");
		$("#dni").val("");
	});

		/**
		 * Elimina una asignatura
		 */
	$("#eliminar_asig").click(function(){
		codigo_asig =  $("#codigo_asig").val();
		//si hay datos
		if(codigo_asig != ""){
			//Ajax --> enviar a servler modificar
			$.ajax({
				type: "POST", //Tipo de envío
				dataType: "html", //Tipo de archivo
				url: "./ServletElimAsig", //Servlet de unión
				data: $.param({ //parametros de envio OBVIAMENTE ESTO HABRÁ QUE HACERLO COGIENDO EL VALOR DE UN FORM
					codigo_asig
				}),
				success: function(result){
					$("#textarea").val(result);
				},
				error: function(error){
					alert("Error:\nAlgo ha ido mal durante la modificación");
					$("#textarea").val("");
				}
			});
		}else{
			//si faltan datos muestra error
			alert("Error:\nDebes rellenar al menos el campo de la asignaturta")
		}
		$("#codigo_asig").val("");
		$("#nombre_asig").val("");
		$("#creditos").val("");
		$("#dni").val("");
	});

	/**
	 * Listar asignaturas
	 */
	$("#listar_asig").click(function(){
		//si hay datos
			//Ajax --> enviar a servler modificar
			$.ajax({
				type: "POST", //Tipo de envío
				dataType: "html", //Tipo de archivo
				url: "./ServletListarAsig", //Servlet de unión
				success: function(result){
					$("#textarea").val(result);
				},
				error: function(error){
					alert("Error:\nAlgo ha ido mal durante la consutla");
					$("#textarea").val("");
				}
			});
		$("#codigo_asig").val("");
		$("#nombre_asig").val("");
		$("#creditos").val("");
		$("#dni").val("");
	});


	/**
	 * Modificar una signatura
	 */
	$("#modificar_asig").click(function(){
		codigo_asig =  $("#codigo_asig").val();
		nombre_asig = $("#nombre_asig").val();
		creditos = $("#creditos").val();
		//si hay datos
		if(codigo_asig != "" && nombre_asig != "" && creditos != ""){
			//Ajax --> enviar a servler modificar
			$.ajax({
				type: "POST", //Tipo de envío
				dataType: "html", //Tipo de archivo
				url: "./ServletModificaAsig", //Servlet de unión
				data: $.param({ //parametros de envio OBVIAMENTE ESTO HABRÁ QUE HACERLO COGIENDO EL VALOR DE UN FORM
					codigo_asig,
					nombre_asig,
					creditos,
				}),
				success: function(result){
					$("#textarea").val(result);
				},
				error: function(error){
					alert("Error:\nAlgo ha ido mal durante la modificación");
					$("#textarea").val("");
				}
			});
		}else{
			//si faltan datos muestra error
			alert("Error:\nDebes rellenar todos los campos referentes a una asignatura")
		}
		$("#codigo_asig").val("");
		$("#nombre_asig").val("");
		$("#creditos").val("");
		$("#dni").val("");
	});

	/**
	 * Matricular alumno en asignatura
	 */
	$("#matricular").click(function(){
		codigo_asig =  $("#codigo_asig").val();
		dni = $("#dni").val();
		//si hay datos
		if(codigo_asig != "" && dni != ""){
			//Ajax --> enviar a servler modificar
			$.ajax({
				type: "POST", //Tipo de envío
				dataType: "html", //Tipo de archivo
				url: "./ServletMatricular", //Servlet de unión
				data: $.param({ //parametros de envio OBVIAMENTE ESTO HABRÁ QUE HACERLO COGIENDO EL VALOR DE UN FORM
					codigo_asig,
					dni
				}),
				success: function(result){
					$("#textarea").val(result);
				},
				error: function(error){
					alert("Error:\nAlgo ha ido mal durante la modificación");
					$("#textarea").val("");
				}
			});
		}else{
			//si faltan datos muestra error
			alert("Error:\nDebes rellenar al menos el código y el dni!!")
		}
		$("#codigo_asig").val("");
		$("#nombre_asig").val("");
		$("#creditos").val("");
		$("#dni").val("");
	});

	/**
	 * Muestra las asignaturas de un alumno
	 */
	$("#alumnos_asig").click(function(){
		codigo_asig =  $("#codigo_asig").val();
		//si hay datos
		if(codigo_asig != ""){
			//Ajax --> enviar a servler modificar
			$.ajax({
				type: "POST", //Tipo de envío
				dataType: "html", //Tipo de archivo
				url: "./ServletAlumnosAsignatura", //Servlet de unión
				data: $.param({ //parametros de envio OBVIAMENTE ESTO HABRÁ QUE HACERLO COGIENDO EL VALOR DE UN FORM
					codigo_asig
				}),
				success: function(result){
					$("#textarea").val(result);
				},
				error: function(error){
					alert("Error:\nAlgo ha ido mal durante la modificación");
					$("#textarea").val("");
				}
			});
		}else{
			//si faltan datos muestra error
			alert("Error:\nDebes rellenar al menos el campo referente al código de la asignatura")
		}
		$("#codigo_asig").val("");
		$("#nombre_asig").val("");
		$("#creditos").val("");
		$("#dni").val("");
	});
	
	
});