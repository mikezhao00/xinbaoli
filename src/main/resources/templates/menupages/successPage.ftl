<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
     <link rel="stylesheet" type="text/css" href="/css/bootstrap/bootstrap.css">
     <link rel="stylesheet" type="text/css" href="/css/bootstrap/bootstrap-table.css">
     <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
     <script type="text/javascript" src="/js/bootstrap/bootstrap.js"></script>
     <script type="text/javascript" src="/js/bootstrap/bootstrap-table.js"></script>
     <script type="text/javascript" src="/js/bootstrap/bootstrap-table-zh-CN.js"></script>
     <script type="text/javascript" src="/js/jquery.serializejson.js"></script>
     
     <style type="text/css">
	     .container {
				width: 90%;
	    		background-color: #00fff3;
	    		height:40px;
				}
		  .container1 {
				width: 10%;
	    		background-color: #00fff3;
	    		height:40px;
				}
		.left{ float:left}
		.right{ float:right; width:200px; border:1px solid #00F}
     </style>
</head>
<body>
<!--头部 start -->
<div class="container left">
	  <ul class="nav nav-pills">
    <li class="active"><a href="/auth/success">首页</a></li>
    <li>
    	 <a class="dropdown-toggle" data-toggle="dropdown" href="#">
       客户管理 <span class="caret"></span>
      </a>
      <ul class="dropdown-menu">
        <li><a href="/customerMng/goCustomerMngPage">企业信息管理</a></li>
        <li><a href="#">jMeter</a></li>
        <li><a href="#">EJB</a></li>
        <li class="divider"></li>
        <li><a href="#">分离的链接</a></li>
      </ul>
    
    </li>
    <li><a href="#">授信管理</a></li>
    <li><a href="#">提款管理</a></li>
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">
        资产管理 <span class="caret"></span>
      </a>
      <ul class="dropdown-menu">
        <li><a href="#">Swing</a></li>
        <li><a href="#">jMeter</a></li>
        <li><a href="#">EJB</a></li>
        <li class="divider"></li>
        <li><a href="#">分离的链接</a></li>
      </ul>
    </li>
    <li><a href="#">应收账款</a></li>
  </ul>
  
</div>
<div class="container1 left">
    <span>当前用户：${Session.user.userName}</span>

</div>
<!--头部 end -->
<!--页面主体 start -->
    <div class="panel-body" style="padding-bottom:0px;">
        <div class="panel panel-default">
            <div class="panel-heading">代办任务查询</div>
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal">
                    <div class="form-group" style="margin-top:15px">
                        <label class="control-label col-sm-1" for="txt_search_departmentname">流程编号</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="txt_search_departmentname">
                        </div>
                        <label class="control-label col-sm-1" for="txt_search_statu">流程名称</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="txt_search_statu">
                        </div>
                        <div class="col-sm-4" style="text-align:left;">
                            <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-primary">查询</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>      
         
        <table id="tb_departments"></table>
    </div>

<!--页面主体 end -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					企业客户管理
				</h4>
			</div>
			<div class="modal-body">
				<form id="customerForm" class="form-horizontal" role="form">
                    <fieldset>
                        <legend>企业基本信息</legend>
                       <div class="form-group">
                          <label class="col-sm-2 control-label" for="cusName">企业名称</label>
                          <div class="col-sm-4">
                             <input class="form-control" id="cusName" name="cusName" type="text" />
                          </div>
                          <label class="col-sm-2 control-label" for="manager">法人</label>
                          <div class="col-sm-4">
                             <input class="form-control" id="manager" name="manager" type="text" />
                          </div>
                       </div>
                       <div class="form-group">
                          <label class="col-sm-2 control-label" for="phone">联系电话</label>
                          <div class="col-sm-4">
                             <input class="form-control" id="phone" name="phone" type="text" />
                          </div>
                          <label class="col-sm-2 control-label" for="address">企业地址</label>
                          <div class="col-sm-4">
                             <input class="form-control" id="address" name="address" type="text" />
                          </div>
                       </div>
                       <div class="form-group">
                          <label class="col-sm-2 control-label" for="cusNo">企业编号</label>
                          <div class="col-sm-4">
                             <input class="form-control" id="cusNo" name="cusNo" type="text" />
                          </div>
                       </div>
                        <div class="form-group">
                          <label class="col-sm-2 control-label" for="remarks">备注</label>
                          <div class="col-md-10">
							 <textarea class="form-control" id="remarks" name="remarks" rows="8" ></textarea>
                          </div>
                       </div>
                    </fieldset>     
                </form>
			</div>
			<div class="modal-footer">
		  			<div style="margin: 0px auto;display: table;" role="group">
						<button  type="button" class="btn btn-default" data-dismiss="modal">返回
						</button>
						<button id="saveBtn" type="button" class="btn btn-primary">
							暂存
						</button>  
						<button id="submitBtn" type="button" class="btn btn-primary">
							提交
						</button>  
						<button id="rebackBtn" type="button" class="btn btn-primary">
							驳回
						</button>  
					</div>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</body>

</html>
<script type="text/javascript">
	$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_departments').bootstrapTable({
            url: '/customerMng/queryCustomer',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'cusName',
                title: '流程ID'
            }, {
                field: 'cusNo',
                title: '流程名称'
            }, {
                field: 'address',
                title: '企业名称'
            }, {
                field:'id',
                title: '操作',
                width: 120,
                align: 'center',
                valign: 'middle',
                formatter: actionFormatter
                 }, ]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            page: (params.offset / params.limit) + 1,  //页码
            cusName: $("#txt_search_departmentname").val(),
            statu: $("#txt_search_statu").val()
        };
        return temp;
    };
    return oTableInit;
};


var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    };

    return oInit;
};

		//操作栏的格式化
        function actionFormatter(value, row, index) {
            var id = value;
            var result = "";
            result += "<a href='javascript:;' class='btn btn-xs green' data-target='#myModal' onclick=\"EditViewById('" + id + "', view='view')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
            result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"EditViewById('" + id + "')\" title='编辑' data-toggle='modal' data-target='#myModal'><span class='glyphicon glyphicon-pencil'></span></a>";
            return result;
        }
        
        //保存操作
        $("#saveBtn").click(function(){
         $.ajax({

             type: "post",

             url: "/customerMng/saveCustomer",

       //      data: $("#customerForm").serialize(), //适应于contentTyp:application/x-www-form-urlencoded提交方式

             data: JSON.stringify($("#customerForm").serializeJSON()),//适应于contentTyp:application/json提交方式

          	//   dataType: "json",
             
             contentType: "application/json",

             success: function(data){
						alert("保存成功");
                      },
        error : function(e) {
            alert(e);
        }

         });
        })
        
        function EditViewById(id){
        	$("#cusName").val(id);
        }
</script>