<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>task Application</title>

    <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
  </head>
  <body>
  <div class="container">
    <h2>task application</h2>
    <div class="actions" style="padding:15px 0px;">
    <button class="btn btn-primary" data-toggle="modal" data-target="#myModal">
      Add entry
    </button>
    </div>
    <div class="content">
    <table class="table table-hover">
      <thead>
        <tr>
          <th>id</th>
                      <th>title</th>
                      <th>desc</th>
                  </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
    </div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">Add entry</h4>
          </div>
          <div class="modal-body">
            <form id="addEntry" role="form">
                              <div class="form-group">
                <label for="title">title</label>
                <input type="text" class="form-control" name="title" id="title">
              </div>
                              <div class="form-group">
                <label for="desc">desc</label>
                <input type="text" class="form-control" name="desc" id="desc">
              </div>
                            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save</button>
              </div>
           </form>
          </div>
      </div>
    </div>
  </div>
  </div>


<script type="text/javascript">
jQuery(document).ready(function(){
  jQuery.ajax({
      url: window.location.origin +"/restconf/operational/task:task",
      dataType: "json",
      success: function(data) {
        jQuery.each(data.task.entry,
          function(){
            var rowEntry = "<tr><td>" +
            this['entry-id']+"</td>"
                          + "<td>" + this.title + "</td>"
                          + "<td>" + this.desc + "</td>"
            
            +"</tr>";
            console.log(rowEntry);
            jQuery(".table tbody").append(rowEntry);
        });
      },
      error : function(){ console.log("no data found");}
  });
});

jQuery("form").on( "submit", function( event ) {
      event.preventDefault();
      var entryId = Math.random()*100000000000000000;
      var formData = '{"input":{"entryField":['
             + '{"key":"title", "value":"'+ jQuery("input[name='title']").val()+ '"}'
                  +','
                     + '{"key":"desc", "value":"'+ jQuery("input[name='desc']").val()+ '"}'
                    +'], "entryId" : "'+entryId+'" } }';
      console.log( jQuery( this ).serialize() );
      jQuery.ajax({
          url: window.location.origin +"/restconf/operations/task:saveEntry",
          type: "POST",
          headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
          },
          data: formData,
          dataType: "json",
          success: function(data) {
              location.reload(true);
          },
          error : function(){ console.log("Error");}
      });

    });
</script>

  </body>
</html>