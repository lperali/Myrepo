$(function() {
        var dialog, form,
        modelName = $( "#modelName" ),
        price = $( "#price" ),
        quantity = $( "#quantity" ),
        modelDescription = $( "#modelDescription" ),
        brandId = $( "#brandId" ),
        allFields = $( [] ).add( modelName ).add( price ).add( quantity ).add( modelDescription ).add( brandId),
        tips = $( ".validateTips" );

        function updateTips( t ) {
        tips
        .text( t )
        .addClass( "ui-state-highlight" );
        setTimeout(function() {
            tips.removeClass( "ui-state-highlight", 1500 );
            }, 500 );
        }

        function checkLength( o, n, min, max ) {
        if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips( "Length of " + n + " must be between " +
            min + " and " + max + "." );
        return false;
        } else {
            return true;
        }
        }

        function checkRegexp( o, regexp, n ) {
            if ( !( regexp.test( o.val() ) ) ) {
                o.addClass( "ui-state-error" );
                updateTips( n );
                return false;
            } else {
                return true;
            }
        }

        function addModel() {
            var valid = true;
            allFields.removeClass( "ui-state-error" );

            valid = valid && checkLength(modelName, "Name", 3, 30);
            valid = valid && checkLength(price, "Price", 3, 10);
            valid = valid && checkLength(quantity, "Quantity", 1, 5);
            valid = valid && checkLength(modelDescription, "Description", 4, 100 );
            valid = valid && checkLength(brandId, "Id", 3, 6);
            alert(modelName.val());
            if ( valid ) {
                var $form = $( this ),
                    url = "http://172.20.105.121:8080/myShop1.0/rest/model/insert";
                $.post( url, { modelName: modelName.val(), price: price.val(), quantity: quantity.val(), modelDescription: modelDescription.val(), brandId: brandId.val() })
                      .done(function( data ) {
                                  alert( "Data Loaded: " + data );
                                    });

                dialog.dialog( "close" );
            }
            return valid;
        }

        dialog = $( "#log-form" ).dialog({
autoOpen: false,
height: 300,
width: 350,
modal: true,
buttons: {
"Create": addModel,
Cancel: function() {
dialog.dialog( "close" );
}
},
close: function() {
form[ 0 ].reset();
allFields.removeClass( "ui-state-error" );
}
});

form = dialog.find( "form" ).on( "submit", function( event ) {
        event.preventDefault();
        });
$(function(){
$( "#add-model" ).button().on( "click", function() {
        dialog.dialog( "open" );
        });
});
});
