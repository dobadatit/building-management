 $('#btnAddUser').click(function () {
        //call ipa addbuilding
        var data = {};   // chua gia tri truyen vao
		var id = 0;
        var formData = $('#formEditUser').serializeArray();  // lay tat ca du lieu tu form data
        $.each(formData, function (index, v) {
			if(v.name == 'id'){
				id = v.value;
			}
                data["" + v.name + ""] = v.value;
        });
 		if(id >0){
			assigStaffUpdate(data);
    	}else{
        	assigStaffInsert(data);
		}
    });
function assigStaffInsert(data) {
		   $.ajax({
            type: "POST",
  		    url: "http://localhost:8080/Real_Estate_M/api-user",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {  
               window.location.href ="http://localhost:8080/Real_Estate_M/admin-user?action=LIST";
            },
            error: function (response) {
                  window.location.href ="http://localhost:8080/Real_Estate_M/admin-user?action=EDIT";
            }
        });
	}
	function assigStaffUpdate(data) {
		   $.ajax({
            type: "PUT",
  		    url: "http://localhost:8080/Real_Estate_M/api-user",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {  
                window.location.href ="http://localhost:8080/Real_Estate_M/admin-user?action=LIST";
            },
            error: function (response) {
                console.log('failed');
                console.log(response);
            }
        });
	}