$( document ).ready(function() {    

    $(document).on("input paste keyup", ".product_qty", function( event ) {         
        
        var product_quantity = 0;
        var product_price = 0;
        var gst_amount = 0;

        var sub_total = 0;
        /* var total_qty = 0;  */
        var grand_total = 0

        product_quantity = $(this).val();
        product_price = $(this).parent().prev().html();

        sub_total = product_price * product_quantity;

        $(this).parent().next().html(sub_total);


        $('.product_qty' ).each( function( k, v ) {
            product_quantity = parseInt ( $(this).val() ) ? parseInt ( $(this).val() ) : 0;
            product_price = parseFloat($(this).parent().prev().html())?parseFloat($(this).parent().prev().html()):0;

            console.log(product_quantity);
            console.log(product_price);            

            sub_total = parseFloat ( product_price * product_quantity );

            console.log(sub_total);

            /* total_qty +=product_quantity; */

            grand_total += sub_total;

        });

        if ( grand_total > 0 ){
            gst_amount = ( grand_total * 6 ) /100;
        }
         
       /*  $("#total_qty").html(total_qty);  */       
        $("#total_amount").html(grand_total);        

        grand_total +=gst_amount;

        $("#gst_amount").html(gst_amount);        
        /* $("#discount_amount").html(0);   */      
        $("#grand_total").html(grand_total);  
      
    });
    //
    /* $(document).on("click", ".delete", function( event ) {
        
        var cart_item = 0;
        $(this).parent().parent().remove();

        cart_item = $('.product_qty').length;
        if ( cart_item <= 0 ) 
        {
            $("#total_qty").html('0');        
            $("#total_amount").html('0');        
            $("#gst_amount").html('0');        
            $("#discount_amount").html(0);        
            $("#grand_total").html('0');             
        } else {
            $('.product_qty').trigger('keyup');  
        }      
        
    });  */
    
    
});
// End Document Ready