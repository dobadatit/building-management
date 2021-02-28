$('#btnDeleteUser').click(function(e) {
	e.preventDefault();
	var data = {};
	var ids = $('#userList').find('tbody input[type=checkbox]:checked').map(function() {
	return $(this).val();}).get();
	data['ids'] = ids;
	deleteUser(data);
});
	function deleteUser(data) {
		$.ajax({
			type : "DELETE",
			url : "http://localhost:8080/Real_Estate_M/api-user",
			data : JSON.stringify(data),
			dataType : "json",
			contentType : "application/json",
			success : function(response) {
				window.location.href ="http://localhost:8080/Real_Estate_M/admin-user?action=LIST";
			},
			error : function(response) {
				console.log('failed');
				console.log(response);
			}
		});
	}