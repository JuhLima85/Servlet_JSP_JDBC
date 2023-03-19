/**
 * Confirmar a exclusão de um contato
 * @author Juliana Lima acessorada por Professor José de Assis.
 * @param id
 */

 function confirmar(id){
	 let resposta = confirm("Certeza que deseja excluir este contato ?")	  
	 if(resposta === true){
		 //Teste de recebimento
		 //alert(id)		 
		 // Encaminha a requisão de exclusão ao servlet (controller)		 
		 window.location.href = "deletar?id=" + id	 
	 }
 }