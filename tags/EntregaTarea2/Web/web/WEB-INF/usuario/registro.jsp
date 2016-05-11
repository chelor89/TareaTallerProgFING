<%-- 
    Document   : registro
    Created on : 21/09/2014, 09:31:12 PM
    Author     : Marcelo
--%>

<script>
$(document).ready(function() {
    
    $(function () {
        $('#dtpicker').datetimepicker({
            pickTime: false,
            maxDate: new Date()
        });
    });
    
    
    $('#registrationForm').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: 'El usuario no es valido',
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    },
                    remote: {                                   // http://bootstrapvalidator.com/validators/remote/
                        message: 'El usuario ya existe',
                        url: '/registro?tipo=chequearUsuario'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {  
                        message: 'Este campo no puede estar vacio'
                    },
                    emailAddress: {
                        message: 'La direccion de email no es valida'
                    },
                    remote: {
                        message: 'El mail ya existe',
                        url: '/registro?tipo=chequearMail'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    },
                    identical: {
                        field: 'password2',
                        message: 'La contraseña y su confirmacion no son las mismas'
                    }
                }
            },
            birthday: {
                feedbackIcons: 'false',
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    },
                    date: {
                        format: 'DD/MM/YYYY',
                        message: 'La fecha no es valida'
                    }
                }
            },
            password2: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    },
                    identical: {
                        field: 'password',
                        message: 'La contraseña y su confirmacion no son las mismas'
                    }
                }
            },
            nombre: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            apellido: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            direccion: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            }
        }
    });
    
});

</script>
    
    <div id="otherModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Modal title</h4>
                </div>
                <div class="modal-body">
                    <p>El usuario se ha registrado con exito&hellip;</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Aceptar</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 style="margin-left:40%" id="myModalLabel">Registro</h3>
                </div>
                <div class="modal-body">
                    <form id="registrationForm" enctype="multipart/form-data" method="post" class="form-horizontal" action="registro">
                    
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Usuario:</label>
                        <div class="col-lg-7">
                            <input type="text" class="form-control cle" name="username" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Email:</label>
                        <div class="col-lg-7">
                            <input type="text" class="form-control cle" name="email" />
                        </div>
                    </div>    
                            
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Contraseña:</label>
                        <div class="col-lg-7">
                            <input type="password" class="form-control cle" name="password" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">Confirmar contraseña:</label>
                        <div class="col-lg-7">
                            <input type="password" class="form-control cle" name="password2" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">Nombre:</label>
                        <div class="col-lg-7">
                            <input type="text" class="form-control cle" name="nombre" />
                        </div>
                    </div>
                        
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Apellido:</label>
                        <div class="col-lg-7">
                            <input type="text" class="form-control cle" name="apellido" />
                        </div>
                    </div>
                        
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Direccion:</label>
                        <div class="col-lg-7">
                            <input type="text" class="form-control cle" name="direccion" />
                        </div>
                    </div>
                        
                    <!--http://eonasdan.github.io/bootstrap-datetimepicker/#example4-->
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Fecha de nacimiento:</label>
                        <div class="col-lg-7">
                            <div class='input-group date' id='dtpicker'>
                                <input type='text' class="form-control cle" name="birthday" data-date-format="DD/MM/YYYY" />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Seleccionar una imagen</label>
                        <div class="col-lg-7">
                            <input class="cle" type="file" name="imagen" > 
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="col-lg-9 col-lg-offset-3">
                            <!-- Do NOT use name="submit" or id="submit" for the Submit button -->
                            <button type="submit" class="btn btn-default">Registrarse</button>
                            <button class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</button>
                        </div>
                    </div>   
                </form>
                </div>
            </div>
            
            <div class="modal-footer">
              
            </div>
        </div>
    </div>
