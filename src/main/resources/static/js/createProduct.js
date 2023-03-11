
$(document).ready(function() {
    // display inputted image
    $("#image-input").on("change", function () {
        let reader = new FileReader();
        reader.addEventListener("load", () => {
            $("#image-preview").attr("src", reader.result);
            $("#image-preview").css("display", "block");
            $("#image-upload").css("display", "none");
        });
        reader.readAsDataURL(this.files[0]);
    });

    // clear input fields
    $("#add-new-button").on("click", function() {
        $.each(["name","description","price","stock","image"], function(index, value) {
            $("[name="+value+"]").val('');
        });
        $("#image-preview").css("display", "none");
        $("#image-upload").css("display", "block");
    });

    // validate file input
    $('#simpan-button').on("click", function(e) {
        // checks image files
        if (!$('#image-input')[0].files || !$('#image-input')[0].files[0]) {
            e.preventDefault();
            alert('Masukkan foto produk');
            return;
        }
        
        // delete trailing spaces
        $.each(["name","description","price","stock"], function(index, value) {
            let trimmedValue = $("[name="+value+"]").val().trim();
            $("[name="+value+"]").val(trimmedValue);
        });

        // make sure all fields are filled
        let requiredFields = $("form").find('[required]').filter(function() {
            return $(this).val() === '';
        });
        if (requiredFields.length == 0) {
            e.preventDefault();
            $("#save-popup").modal("show");
        } // else display default input error message
    });

    $(".confirm-button").on("click", function() {
        show("#loading-content");
        hide("#confirmation-popup-content");
        hide("#fail-popup-content");

        // create a new FormData object
        let formData = new FormData();
    
        // append the file and request parameters to the FormData object
        $.each(["name","description","price","stock"], function(index, value) {
            formData.append(value, $("[name="+value+"]").val());
        });
        formData.append("image", $("[name=image]")[0].files[0]);

        // post create product request
        $.ajax({
            url: $("form").attr("action"),
            type: "POST",
            data: formData,
            dataType: "json",
            contentType: false,
            processData: false,
            success: function() {
                hide("#loading-content");
                show("#confirmation-popup-content");
            },
            error: function() {
                hide("#loading-content");
                show("#fail-popup-content");
            }
        });
    });

    // set visibility for fade animation
    function show(element) {
        $(element).show();
        $(element).css("visibility","visible");
        $(element).css("opacity","1");
    }
    function hide(element) {
        $(element).hide();
        $(element).css("visibility","hidden");
        $(element).css("opacity","0");
    }
});