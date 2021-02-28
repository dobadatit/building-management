
  $('#btnAddBuilding').click(function () {
        //call ipa addbuilding
        var data = {};   // chua gia tri truyen vao
        var buildingTypes = [];
		var id = 0;
        var formData = $('#formEdit').serializeArray();  // lay tat ca du lieu tu form data
        $.each(formData, function (index, v) {
			if(v.name == 'id'){
				id = v.value;
			}
            if (v.name == 'buildingTypes' ) {
                buildingTypes.push(v.value)
            } else {
                data["" + v.name + ""] = v.value;
            }
        });
        data['buildingTypes'] = buildingTypes;
 		if(id >0){
			buildingUpdate(data);
    	}else{
        	buildingInsert(data);
		}
    });
function buildingInsert(data) {
		   $.ajax({
            type: "POST",
  		    url: "http://localhost:8080/Real_Estate_M/api-building",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {  
               window.location.href ="http://localhost:8080/Real_Estate_M/admin-building?action=LIST&page=1&limit=4";
            },
            error: function (response) {
                console.log('failed');
                console.log(response);
            }
        });
	}
	function buildingUpdate(data) {
		   $.ajax({
            type: "PUT",
  		    url: "http://localhost:8080/Real_Estate_M/api-building",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {  
                window.location.href ="http://localhost:8080/Real_Estate_M/admin-building?action=LIST&page=1&limit=4";
            },
            error: function (response) {
                console.log('failed');
                console.log(response);
            }
        });
	}