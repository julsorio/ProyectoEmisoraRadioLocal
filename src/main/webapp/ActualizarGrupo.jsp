<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/newportal.css">

<title>Grupos Musicales - Actualizar grupo musical</title>

</head>

<body class="fondo">

	<div class="container">
		<div class="signup-form-container">

			<!-- Espacio de cabecera -->
			<div class="form-header">
				<h3 class="registration">
					<i class="fa fa-user"></i>Sistema de Gestión de Emisora Local
				</h3>
			</div>

			<!-- Espacio de enlace de Volver-->
			<div class="form-row">
				<div class="col">
					<a href="${pageContext.request.contextPath}/ServletEmisora?accion=volver">Volver</a>
				</div>
			</div>

			<p></p>

			<!-- Espacio para modificar los datos -->
			<div class="form-row">
				<div class="col-3"></div>
				<div class="col-6">
					<div class="form-body">
						<div class="form-group">
							<div>
								<form role="form" id="login-usuarios" autocomplete="off"
									class="credentials" method="get" action="ServletEmisora">
									<input type="hidden" id="accion" name="accion" value="actualizar" >
									Actualizar grupo musical
									<p></p>

									<div class="input-group">
										<input name="grupoId" type="text" readonly="readonly"
											class="form-control fontAwesome"
											value="${grupo.grupoId}">
									</div>
									<p></p>

									<div class="input-group">
										<input name="nombre" type="text"
											class="form-control fontAwesome" value="${grupo.nombre}">
									</div>
									<p></p>
									<div class="input-group">
										<input name="creacion" type="text"
											class="form-control fontAwesome"
											value="${grupo.creacion}">
									</div>
									<p></p>

									<div class="input-group">
										<select name="origen" id="origen"
											class="form-control fontAwesome">
											<option value="Estados Unidos" <c:if test="${grupo.origen eq 'Estados Unidos'}"> selected="selected"</c:if>>Estados Unidos</option>
											<option value="Reino Unido" <c:if test="${grupo.origen eq 'Reino Unido'}"> selected="selected"</c:if>>Reino Unido</option>
											<option value="Australia" <c:if test="${grupo.origen eq 'Australia'}"> selected="selected"</c:if>>Australia</option>
											<option value="España" <c:if test="${grupo.origen eq 'España'}"> selected="selected"</c:if>>España</option>
											<option value="Alemania" <c:if test="${grupo.origen eq 'Alemania'}"> selected="selected"</c:if>>Alemania</option>
										</select>
									</div>
									<p></p>

									<div class="input-group">
										<select name="genero" id="genero"
											class="form-control fontAwesome">
												<option value="Heavy Metal" <c:if test="${grupo.genero eq 'Heavy Metal'}"> selected="selected" </c:if> >Heavy Metal</option>
												<option value="Hard rock" <c:if test="${grupo.genero eq 'Hard rock'}"> selected="selected" </c:if> >Hard rock</option>
												<option value="Rock" <c:if test="${grupo.genero eq 'Rock'}"> selected="selected" </c:if> >Rock</option>
												<option value="Folk metal" <c:if test="${grupo.genero eq 'Folk metal'}"> selected="selected" </c:if> >Folk metal</option>
												<option value="Rock andaluz" <c:if test="${grupo.genero eq 'Rock andaluz'}"> selected="selected" </c:if> >Rock andaluz</option>
										</select>
									</div>
									<p></p>

									<span class="input-group-btn">
										<button type="submit" class="btn btn-info">Guardar</button>
									</span> <span class="input-group-btn">
										<button type="reset" class="btn btn-info"
											onclick="history.back();">Cancelar</button>
									</span>

								</form>
							</div>

						</div>
					</div>
				</div>

			</div>

			<!-- Espacio para el mensaje -->
			<div class="form-row">
				<div class="col error">
					<c:out value="${requestScope.error}"></c:out>
				</div>
			</div>

		</div>
	</div>

</body>
</html>
