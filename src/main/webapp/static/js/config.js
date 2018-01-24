var  publicPathSP = "";
var local = window.location.host;
    if(local.indexOf("b.jiacedu.com")!=-1){
        publicPathSP = "http://common.jiacedu.com";
    }else{
        publicPathSP = "http://test.jiacersxy.com:8000";
    }
//系统请求后端路径
var config = {
    sysUserResetPwdUrl:ctx + "/system/user/resetPwd", //系统用户密码修改
    sysUserQueryUrl: ctx + "/system/user/query", //系统用户查询
    sysUserRoleQueryUrl: ctx + "/system/user/rolequery", //系统用户角色查询
    sysUserFormUrl: ctx + "/system/user/form", //系统用户form
    sysUserDeleteUrl: ctx + "/system/user/delete", //系统用户form
    sysUserAddUrl: ctx + "/system/user/add", //系ordersQueryUrl统用户form
    sysUserModifyUrl: ctx + "/system/user/modify", //系统用户form
    sysUserListUrl: ctx + "/system/user/list", //系统用户查询
    getIsShowSysMenuUrl: ctx + "/system/constants/isShowSysMenu",//是否显示其他系统菜单
    
    coursesQueryUrl: ctx + "/courses/query",
    coursesQuerySTUUrl: ctx + "/courses/querySTU",
    coursesFormUrl: ctx + "/courses/form",
    coursesAddUrl: ctx + "/courses/add",
    coursesModifyUrl: ctx + "/courses/modify",
    coursesListUrl: ctx + "/courses/list",
    coursesPackIdListUrl: ctx + "/courses/getLearnTypesList", //根据 course_package_id 获取learn_types 列表
    getAuthenticateGradeName: ctx + "/courses/getAuthenticateGradeName",
    getCertNameUrl:ctx+"/courses/certName",

    schoolsQueryUrl: ctx + "/schools/query",
    schoolsFormUrl: ctx + "/schools/form",
    schoolsAddUrl: ctx + "/schools/add",
    schoolsModifyUrl: ctx + "/schools/modify",
    schoolsListUrl: ctx + "/schools/list",

    coursesTimeQueryUrl: ctx + "/coursesTime/query",
    coursesTimeFormUrl: ctx + "/coursesTime/form",
    coursesTimeAddUrl: ctx + "/coursesTime/add",
    coursesTimeModifyUrl: ctx + "/coursesTime/modify",
    coursesTimeListUrl: ctx + "/coursesTime/list",

    usersQueryUrl: ctx + "/user/query",
    usersFormUrl: ctx + "/user/form",
    usersAddUrl: ctx + "/user/add",
    usersModifyUrl: ctx + "/user/modify",
    usersInfoUrl: ctx + "/user/info",
    usersListUrl: ctx + "/user/list",

    productQueryUrl: ctx + "/coursesPackage/query",
    productFormUrl: ctx + "/coursesPackage/form",
    productAddUrl: ctx + "/coursesPackage/add",
    productModifyUrl: ctx + "/coursesPackage/modify",
    productListUrl: ctx + "/coursesPackage/list",

    schoolsIntroUrl: ctx + "/intro/updateInstitution",

    brokerageQueryUrl: ctx + "/brokerage/query",
    brokerageFormUrl: ctx + "/brokerage/form",
    brokerageAddUrl: ctx + "/brokerage/add",
    brokerageModifyUrl: ctx + "/brokerage/modify",
    brokerageListUrl: ctx + "/brokerage/list",

    partnersQueryUrl: ctx + "/partners/query",
    partnersFormUrl: ctx + "/partners/form",
    partnersAddUrl: ctx + "/partners/add",
    partnersModifyUrl: ctx + "/partners/modify",
    partnersListUrl: ctx + "/partners/list",

    examsQueryUrl: ctx + "/exams/query",
    examsFormUrl: ctx + "/exams/form",
    examsAddUrl: ctx + "/exams/add",
    examsModifyUrl: ctx + "/exams/modify",
    examsListUrl: ctx + "/exams/list",
    examsFormGoBack: ctx + "/exams/goback",
    examsLearnTypesListUrl: ctx + "/exams/getExamsList", //根据 learn_types 获取exams_class 列表
    getTimeListUrl: ctx + "/coursesTime/getTimeList", //根据 learn_types 获取exams_class 列表
    examsGetIsExamsAvailable: ctx + "/exams/getIsExamsAvailable",

    ordersQueryUrl: ctx + "/orders/query",
    ordersQueryStuUrl: ctx + "/orders/queryStu",
    ordersFormUrl: ctx + "/orders/form",
    ordersFormYBMUrl: ctx + "/orders/formYBM", //已报名
    ordersAddUrl: ctx + "/orders/add",
    ordersModifyUrl: ctx + "/orders/modify",
    ordersRepListGoBackUrl: ctx + "/orders/repOrderGoBack",
    orderListGoBackUrl: ctx + "/orders/orderGoBack",
    ordersPassUrl: ctx + "/orders/apply/pass",
    ordersUnpassUrl: ctx + "/orders/apply/unpass",
    ordersListUrl: ctx + "/orders/list",
    exportOrdersUrl: ctx + "/orders/export/01",//导出excel表
    
    scoresQueryUrl: ctx + "/scores/query",
    scoresFormUrl: ctx + "/scores/form",
    scoresAddUrl: ctx + "/scores/add",
    scoresModifyUrl: ctx + "/scores/modify",
    scoresListUrl: ctx + "/scores/list",
    scoresInfoUrl: ctx + "/scores/info",
    learnQueryUrl: ctx + "/learnRecords/query",
    learnListUrl: ctx + "/learnRecords/list",
    examsLearnUrl: ctx + "/exams/learnRecords/form",
    imgUploadUrl: publicPathSP + "/image/upload"
};