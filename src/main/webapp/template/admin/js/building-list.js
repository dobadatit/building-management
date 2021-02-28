	function assignmentBuilding(buildingId) {
		openModalAsssignmentBuilding();
		$('#buildingId').val(buildingId); // truyen id vao #buildingId
		console.log($('#buildingId').val());
		//call api load  staff list
		showStaffAssignment(buildingId);
	}
	
// goi modal hien len
	function openModalAsssignmentBuilding() {
		$('#assignmentBuildingModal').modal();
	}
//
	$('#btnAssignBuilding').click(function (e) { 
	    e.preventDefault();
	    var data={};
	    data['buildingId'] =$('#buildingId').val();
	    var staffs =$('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
	        return $(this).val();
	    }).get();
	    data['staffs'] = staffs;
		
	   assigStaff(data);
	});

	function assigStaff(data) {
		$.ajax({
			type : "POST",
			url : "http://localhost:8080/Real_Estate_M/api-user-assignment",
			data : JSON.stringify(data),
			dataType : "json",
			contentType : "application/json",
			success : function(response) {
				window.location.href ="http://localhost:8080/Real_Estate_M/admin-building?action=LIST&page=1&limit=4";
			},
			error : function(response) {
				console.log('failed');
				console.log(response);
			}
		});
	}
	function showStaffAssignment(buildingId) {
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Real_Estate_M/api-user?buildingId="+buildingId,
			dataType : "json",
			success : function(response) {
				var html='';
				$.each(response, function(index, staffOutput){
					html +='<tr>';
					html +='<td><input type="checkbox" value="'+staffOutput.id+'" id="checkbox_'+staffOutput.userName+'" '+staffOutput.checked+'></td>';
					html += '<td>'+staffOutput.userName+'</td>';
					html +='</tr>';
				});
			// html(html) them doan html tren vao t	body
				$('#staffList tbody').html(html);	
			},
			error : function(response) {
				console.log('failed');
				console.log(response);
			}
		});
	}
		
	$('#btnDeleteBuilding').click(function(e) {
				e.preventDefault();
				var data = {};
				var ids = $('#buildingList').find(
						'tbody input[type=checkbox]:checked').map(function() {
					return $(this).val();
				}).get();
				data['ids'] = ids;
				deleteBuilding(data);
			});
	function deleteBuilding(data) {
		$.ajax({
			type : "DELETE",
			url : "http://localhost:8080/Real_Estate_M/api-building",
			data : JSON.stringify(data),
			dataType : "json",
			contentType : "application/json",
			success : function(response) {
				window.location.href ="http://localhost:8080/Real_Estate_M/admin-building?action=LIST&page=1&limit=4";
			},
			error : function(response) {
				console.log('failed');
				console.log(response);
			}
		});
	}
	$('#btnDeleteBuilding').submit(function(e) {
		alert("Submitted");
});

	