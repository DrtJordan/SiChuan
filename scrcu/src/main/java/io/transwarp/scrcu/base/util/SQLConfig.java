package io.transwarp.scrcu.base.util;

public enum SQLConfig {
    // 图
    portal_chart,
    // 概览
    portal_index,
    // 门户网站分析 页面排行
    //实时
    portal_siteAnalysis_real,
    portal_pageRank,
    // 门户网站分析 页面分析
    portal_pageAnalysis,
    // 门户 网站统计 访问分析1 时长
    portal_visitAnalysis_time,
    // 门户 网站统计 访问分析2 深度
    portal_visitAnalysis_depth,
    // 门户 网站分析 会员页面
    portal_siteAnalysis_userVisitPage_query,
    // 用户统计 地域分布
    portal_siteAnalysis_area_query,
    // 用户统计，地域分布 访客数最大值
    portal_siteAnalysis_max_uv,
    // 用户统计 流失统计
    portal_siteAnalysis_userLoss_query,
    // 用户统计 唯一用户分析
    portal_userAnalysis_userOnly,
    // 门户 来源统计 来源
    portal_sourceAnalysis_source,
    // 门户 来源统计 搜索引擎
    portal_sourceAnalysis_searchEngine,
    // 门户 来源统计 入口页面
    portal_siteAnalysis_entryPage_query,
    // 门户 网站统计 广告趋势
    portal_siteAnalysis_adTrend_query,
    // 门户 网站统计 流量趋势
    portal_siteAnalysis_flowTrend,
    //门户 网站统计 流量趋势 chart图表 
    portal_siteAnalysis_flowTrend_chart,
    // 门户 用户统计 用户等级
    portal_userAnalysis_userLevel,
    // 门户 事件统计 事件列表
    portal_eventAnalysis_list,
    // 门户 事件统计 事件明细
    portal_eventAnalysis_detail,
    // 门户 事件统计 事件趋势
    portal_eventAnalysis_tendency,

    // 门户 来源统计 活跃用户 日期纬度
    app_activeUser,
    // 门户 来源统计 活跃用户 手机纬度
    app_activeUser_phone,
    // 门户 来源统计 活跃用户 渠道纬度
    app_activeUser_channel,

    // 门户 终端类型 操作系统占比
    portal_terminal_os,
    // 门户 终端类型 浏览器占比
    portal_terminal_browserDiv,
    //业务分析
    business_analysis,

    // 移动 留存用户分布
    app_retainUser,
    // 移动 手机留存用户分布
    app_retainUser_phone,
    // 移动 渠道留存用户分布
    app_retainUser_channel,
    // 移动 注册用户分析
    app_regUser,
    // 移动 渠道注册分布
    app_regUser_channel,
    // 移动 手机注册分布
    app_regUser_phone,

    // 移动 渠道明细
    app_channel_detail,


    //会员流失分析 按天汇总
    app_member_runoff_day,
    //会员流失分析 按月汇总
    app_member_runoff_month,

    // 全部事件查询
    app_event_list,
    // 事件趋势
    app_event_tendency,
    // 事件详情
    app_event_detail,

    /**
     * 门户统计按照天、月汇总统计
     */
    //页面排行 按天汇总
    portal_pageRank_day,
    //页面排行 按月汇总
    portal_pageRank_month,

    //页面排行 按天汇总
    portal_siteAnalysis_userVisitPage_query_day,
    //页面排行 按月汇总
    portal_siteAnalysis_userVisitPage_query_month,

    //访问时长 按天汇总
    portal_visitAnalysis_time_day,
    //访问时长 按月汇总
    portal_visitAnalysis_time_month,

    //访问深度 按天汇总
    portal_visitAnalysis_depth_day,
    //访问深度 按月汇总
    portal_visitAnalysis_depth_month,

    //访问时长 按季汇总
    portal_visitAnalysis_time_quarter,
    //访问时长 按年汇总
    portal_visitAnalysis_time_year,

    //访问深度 按季汇总
    portal_visitAnalysis_depth_quarter,
    //访问深度 按年汇总
    portal_visitAnalysis_depth_year,

    //广告趋势 按天汇总
    portal_siteAnalysis_adTrend_query_day,
    //广告趋势 按月汇总
    portal_siteAnalysis_adTrend_query_month,

    //地域分布 按天汇总
    portal_siteAnalysis_area_query_day,
    //地域分布 按月汇总
    portal_siteAnalysis_area_query_month,
    //地域分布 按季汇总
    portal_siteAnalysis_area_query_quarter,
    //地域分布 按年汇总
    portal_siteAnalysis_area_query_year,

    //终端类型 操作系统按天汇总
    portal_terminal_os_day,
    //终端类型 浏览器按天汇总
    portal_terminal_browserDiv_day,

    //终端类型 操作系统按月汇总
    portal_terminal_os_month,
    //终端类型 浏览器按月汇总
    portal_terminal_browserDiv_month,

    // 用户统计 流失分析按天汇总
    portal_siteAnalysis_userLoss_query_day,
    // 用户统计 流失分析按周汇总
    portal_siteAnalysis_userLoss_query_week,
    // 用户统计 流失分析按月汇总
    portal_siteAnalysis_userLoss_query_month,
    // 用户统计 流失分析按季汇总
    portal_siteAnalysis_userLoss_query_quarter,
    // 用户统计 流失分析按年汇总
    portal_siteAnalysis_userLoss_query_year,

    //会员页面分析按天汇总
    portal_siteAnalysis_userPage_query_day,
    //会员页面分析按月汇总
    portal_siteAnalysis_userPage_query_month,

    //唯一用户分析 按天汇总
    portal_userAnalysis_userOnly_day,
    //唯一用户分析 按周汇总
    portal_userAnalysis_userOnly_week,
    //唯一用户分析 按月汇总
    portal_userAnalysis_userOnly_month,
    //唯一用户分析 按季汇总
    portal_userAnalysis_userOnly_quarter,
    //唯一用户分析 按年汇总
    portal_userAnalysis_userOnly_year,

    //搜索引擎分析 按天汇总
    portal_sourceAnalysis_searchEngine_day,
    //搜索引擎分析 按月汇总
    portal_sourceAnalysis_searchEngine_month,


    /**
     * App移动统计按天、周、月、季、年汇总统计
     */
    //app留存用户按天汇总
    app_retainUser_day,
    app_retainUser_phone_day,
    app_retainUser_channel_day,
    //app留存用户按周汇总
    app_retainUser_week,
    app_retainUser_phone_week,
    app_retainUser_channel_week,
    //app留存用户按月汇总
    app_retainUser_month,
    app_retainUser_phone_month,
    app_retainUser_channel_month,
    //app留存用户按季汇总
    app_retainUser_quarter,
    app_retainUser_phone_quarter,
    app_retainUser_channel_quarter,
    //app留存用户按年汇总
    app_retainUser_year,
    app_retainUser_phone_year,
    app_retainUser_channel_year,

    //新增用户按天汇总
    app_newUser_day,
    app_newUser_phone_day,
    //新增用户按周汇总
    app_newUser_week,
    app_newUser_phone_week,
    //新增用户按月汇总
    app_newUser_month,
    app_newUser_phone_month,
    //新增用户按季汇总
    app_newUser_quarter,
    app_newUser_phone_quarter,
    //新增用户按年汇总
    app_newUser_year,
    app_newUser_phone_year,

    //活跃用户按天汇总
    app_activeUser_day,
    //活跃用户 手机纬度
    app_activeUser_phone_day,
    //活跃用户 渠道纬度
    app_activeUser_channel_day,
    //活跃用户按周汇总
    app_activeUser_week,
    //活跃用户 手机纬度
    app_activeUser_phone_week,
    //活跃用户 渠道纬度
    app_activeUser_channel_week,

    //活跃用户按月汇总
    app_activeUser_month,
    //活跃用户 手机纬度
    app_activeUser_phone_month,
    //活跃用户 渠道纬度
    app_activeUser_channel_month,
    //活跃用户按季汇总
    app_activeUser_quarter,
    //活跃用户 手机纬度
    app_activeUser_phone_quarter,
    //活跃用户 渠道纬度
    app_activeUser_channel_quarter,
    //活跃用户按季汇总
    app_activeUser_year,
    //活跃用户 手机纬度
    app_activeUser_phone_year,
    //活跃用户 渠道纬度
    app_activeUser_channel_year,

    //注册用户 渠道注册按天汇总
    app_regUser_channel_day,
    //注册用户 手机os注册按天汇总
    app_regUser_os_day,
    //注册用户 渠道注册按月汇总
    app_regUser_channel_month,
    //注册用户 手机os注册按月汇总
    app_regUser_os_month,
    //注册用户 渠道注册按季汇总
    app_regUser_channel_quarter,
    //注册用户 手机os注册按季汇总
    app_regUser_os_quarter,
    //注册用户 渠道注册按年汇总
    app_regUser_channel_year,
    //注册用户 手机os注册按年汇总
    app_regUser_os_year,

    //登陆用户按天汇总
    app_loginUser_day,
    //登陆用户 渠道登录按天汇总
    app_loginUser_channel_day,
    //登陆用户 手机登录按天汇总
    app_loginUser_os_day,

    //登陆用户按月汇总
    app_loginUser_month,
    //登陆用户 渠道登录按月汇总
    app_loginUser_channel_month,
    //登陆用户 手机登录按月汇总
    app_loginUser_os_month,

    //登陆用户按季汇总
    app_loginUser_quarter,
    //登陆用户 渠道登录按季汇总
    app_loginUser_channel_quarter,
    //登陆用户 手机登录按季汇总
    app_loginUser_os_quarter,

    //登陆用户按年汇总
    app_loginUser_year,
    //登陆用户 渠道登录按年汇总
    app_loginUser_channel_year,
    //登陆用户 手机登录按年汇总
    app_loginUser_os_year,

    //使用时长按天汇总
    app_useTime_day,
    app_useTime_channel_day,
    app_useTime_phone_day,
    app_useTime_os_day,
    //使用时长按周汇总
    app_useTime_week,
    app_useTime_channel_week,
    app_useTime_phone_week,
    app_useTime_os_week,
    //使用时长按月汇总
    app_useTime_month,
    app_useTime_channel_month,
    app_useTime_phone_month,
    app_useTime_os_month,
    //使用时长按季汇总
    app_useTime_quarter,
    app_useTime_channel_quarter,
    app_useTime_phone_quarter,
    app_useTime_os_quarter,
    //使用时长按年汇总
    app_useTime_year,
    app_useTime_channel_year,
    app_useTime_phone_year,
    app_useTime_os_year,

    // 移动 访问深度按天分布
    app_depth_day,
    app_depth_phone_day,
    app_depth_channel_day,
    app_depth_os_day,
    // 移动 访问深度按周分布
    app_depth_week,
    app_depth_phone_week,
    app_depth_channel_week,
    app_depth_os_week,
    // 移动 访问深度按月分布
    app_depth_month,
    app_depth_phone_month,
    app_depth_channel_month,
    app_depth_os_month,
    // 移动 访问深度按季分布
    app_depth_quarter,
    app_depth_phone_quarter,
    app_depth_channel_quarter,
    app_depth_os_quarter,
    // 移动 访问深度按年分布
    app_depth_year,
    app_depth_phone_year,
    app_depth_channel_year,
    app_depth_os_year,

    //使用频率按天汇总
    app_useRate_day,
    app_useRate_channel_day,
    app_useRate_phone_day,
    app_useRate_os_day,
    //使用频率按周汇总
    app_useRate_week,
    app_useRate_channel_week,
    app_useRate_phone_week,
    app_useRate_os_week,
    //使用频率按月汇总
    app_useRate_month,
    app_useRate_channel_month,
    app_useRate_phone_month,
    app_useRate_os_month,
    //使用频率按季汇总
    app_useRate_quarter,
    app_useRate_channel_quarter,
    app_useRate_phone_quarter,
    app_useRate_os_quarter,
    //使用频率按年汇总
    app_useRate_year,
    app_useRate_channel_year,
    app_useRate_phone_year,
    app_useRate_os_year,

    //使用间隔按天汇总
    app_interval_day,
    app_interval_channel_day,
    app_interval_phone_day,
    app_interval_os_day,
    //使用间隔按周汇总
    app_interval_week,
    app_interval_channel_week,
    app_interval_phone_week,
    app_interval_os_week,
    //使用间隔按月汇总
    app_interval_month,
    app_interval_channel_month,
    app_interval_phone_month,
    app_interval_os_month,
    //使用间隔按季汇总
    app_interval_quarter,
    app_interval_channel_quarter,
    app_interval_phone_quarter,
    app_interval_os_quarter,
    //使用间隔按年汇总
    app_interval_year,
    app_interval_channel_year,
    app_interval_phone_year,
    app_interval_os_year,

    // 移动 版本分布按天汇总
    app_version_day,
    // 移动 版本分布按月汇总
    app_version_month,
    // 移动 版本分布按季汇总
    app_version_quarter,
    // 移动 版本分布按年汇总
    app_version_year,

    // //移动地域按天汇总
    app_area_query_day,
    app_area_phone_day,
    app_area_chanel_day,
    app_area_os_day,
    // //移动地域按周汇总
    app_area_query_week,
    app_area_phone_week,
    app_area_chanel_week,
    app_area_os_week,
    // //移动地域按月汇总
    app_area_query_month,
    app_area_phone_month,
    app_area_chanel_month,
    app_area_os_month,
    // //移动地域按季汇总
    app_area_query_quarter,
    app_area_phone_quarter,
    app_area_chanel_quarter,
    app_area_os_quarter,
    // //移动地域按年汇总
    app_area_query_year,
    app_area_phone_year,
    app_area_chanel_year,
    app_area_os_year,
    // app地域分布访客数最大值
    app_area_max_uv,

    // 移动 渠道启动次数按天汇总
    app_startCount_channel_day,
    // 移动 手机启动次数按天汇总
    app_startCount_phone_day,
    // 移动 渠道启动次数按月汇总
    app_startCount_channel_month,
    // 移动 手机启动次数按月汇总
    app_startCount_phone_month,
    // 移动 渠道启动次数按季汇总
    app_startCount_channel_quarter,
    // 移动 手机启动次数按季汇总
    app_startCount_phone_quarter,
    // 移动 渠道启动次数按年汇总
    app_startCount_channel_year,
    // 移动 手机启动次数按年汇总
    app_startCount_phone_year,

    // 移动 渠道列表按天汇总
    app_channel_list_day,
    // 移动 渠道列表按周汇总
    app_channel_list_week,
    // 移动 渠道列表按月汇总
    app_channel_list_month,
    // 移动 渠道列表按季汇总
    app_channel_list_quarter,
    // 移动 渠道列表按年汇总
    app_channel_list_year,

    //移动 app会员页面按天汇总
    app_memberPage_day,
    //移动 app会员页面按月汇总
    app_memberPage_month,

    // 画像 交易曲线
    portrait_trading,
    // 画像 交易曲线
    portrait_30_trading,
    // 画像 交易曲线计数
    portrait_trading_sum,
    // 画像 交易次数最大
    portrait_trading_count,
    // 画像 交易金额最大
    portrait_trading_money,
    // 画像 交易占比
    portrait_trading_proportion,
    // 画像 人际关系
    portrait_relation,
    // 画像 用户标签汇总
    portal_userLabelDesc,
    // 当前用户的标签
    portal_userLabel,

    // 画像 上周标签汇总
    portal_userTrends_lastWeek,
    // 画像 交易衰减
    portrait_damping,
    // 画像 交易类型次数总数
    portrait_trading_ratio_sum,
    // 画像 交易类型次数
    portrait_trading_ratio,
    // 静态标签
    label_label_grouping,

    //标签配置
    //标签配置-使用时段
    use_time_label,
    use_time_label_config,
    //标签配置-职业
    job_label,
    job_label_config,
    //标签配置-产品功能消费偏好
    custom_hobby_label,
    custom_hobby_label_config,
    //标签配置-年龄
    age_label,
    age_label_config,
    //标签配置-蜀信e注册年限
    reg_year_label,
    reg_year_label_config,
    //标签配置-安全认证方式
    sec_auth_label,
    sec_auth_label_config,
    //标签配置-支付偏好
    pay_hobby_label,
    pay_hobby_label_config,
    //标签配置-汇路时效
    send_eff_label,
    send_eff_label_config,
    //标签配置-行内外转账
    bank_tran_label,
    bank_tran_label_config,
    //标签配置-交易偏好
    tran_hobby_label,
    tran_hobby_label_config,
    //标签配置-转账类型偏好
    tran_type_label,
    tran_type_label_config,
    //标签配置-存款产品偏好
    prod_hobby_label,
    prod_hobby_label_config,
    //标签配置-搜索引擎
    search_eng_label,
    search_eng_label_config,
    //标签配置—终端
    terminal_label,
    terminal_label_config,
    //标签配置—资产负债
    pro_debt_label,
    pro_debt_label_config,
    //标签配置—持有产品服务
    prod_server_label,
    prod_server_label_config,
    //标签配置—客户参与活动
    part_active_label,
    part_active_label_config,
    //标签配置—月交易次数
    tran_num_label,
    tran_num_label_config,
    //标签配置—月交易金额
    tran_money_label,
    tran_money_label_config,
    //标签配置—时间偏好
    time_hobby_label,
    time_hobby_label_config,
    //标签配置—用户群体类型
    group_type_label,
    group_type_label_config,
    //标签配置—社交关系
    soc_relation_label,
    soc_relation_label_config,
    //标签配置—网页搜索操作行为
    sear_behaviour_label,
    sear_behaviour_label_config,
    //标签配置—网页投诉操作行为
    comp_behaviour_label,
    comp_behaviour_label_config,
    //标签配置—网页事件操作行为
    event_behaviour_label,
    event_behaviour_label_config,
    //标签配置—网页留言操作行为
    note_behaviour_label,
    note_behaviour_label_config,
    //标签配置—活跃功能特征
    func_feature_label,
    func_feature_label_config,
    //标签配置—活跃变化特征
    cha_feature_label,
    cha_feature_label_config,

    // 画像 家庭关系
    portrait_family,
    // 画像 转账关系
    portrait_transfer,
    // 人际列表
    portrait_relation_group,

    // 群体,群体静态标签表总数，需要带标签的条件
    label_staticLabel_colony_grouping_tagCount,
    // 群体,群体动态标签表总数，需要带标签的条件
    label_dynamicLabel_colony_grouping_tagCount,
    // 群体用户
    label_colony_users, users,
    // 群体,群体静态标签表总数，需要带标签的条件
    label_staticLabel_colony_grouping,
    // 群体,群体动态标签表总数，需要带标签的条件
    label_dynamicLabel_colony_grouping,

}
