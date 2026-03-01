<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>Inscription</h2>
  <div>
  <form action="registrer" method="post">
  <fieldset  >
         <p>
           <label>Nom:</label>
           <input type="text" name="nom" id="nom" size="30"/>
         </p>
         <p>
           <label>Email:</label>
           <input type="email" name="email" id="email" size="30"/>
         </p>
         <p>
           <label>Téléphone:</label>
           <input type="tel" name="phone" id="phone" size="30"/>
          </p>
           <label>Mot de passe:</label>
           <input type="password" name="Motdepass" id="Motdepass" size="30"/>
           
          </p>
          <select name="role"> 
             <label>Role:</label>
             <option value="">Choisir le role </option>
             <option value="ADMIN">ADMIN</option>
             <option value="AGENT">AGENT</option>
             <option value="CLIENT">CLIENT</option>  
          </select>
          <select name="statut"> 
             <label>Statut:</label>
             <option value="">Choisir votre statu </option>
             <option value="Actif">Actif</option>
             <option value="Passif">Passif</option>
          </select>
          </p>
           <label>Organisation:</label>
           <input type="text" name="organisationid" id="organisationid" size="30"/>
          </p>
          </p>
           
           <input type="submit" name="ok" value="valider"/>
           <input type="reset" name="No_ok" value="Annuler"/>
          </p>
          
  </fieldset>
 
 </form>
  <p>Déjà un compte ? <a href="Loginfrm.jsp">Connectez-vous</a></p>
  </div>
 
  
</body>
</html>