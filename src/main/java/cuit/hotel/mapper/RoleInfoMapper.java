package cuit.hotel.mapper;

import cuit.hotel.entity.RoleInfo;

public interface RoleInfoMapper {
    /**
     *  根据主键删除数据库的记录,role_info
     *
     * @param roleID
     */
    int deleteByPrimaryKey(Integer roleID);

    /**
     *  新写入数据库记录,role_info
     *
     * @param record
     */
    int insert(RoleInfo record);

    /**
     *  动态字段,写入数据库记录,role_info
     *
     * @param record
     */
    int insertSelective(RoleInfo record);

    /**
     *  根据指定主键获取一条数据库记录,role_info
     *
     * @param roleID
     */
    RoleInfo selectByPrimaryKey(Integer roleID);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,role_info
     *
     * @param record
     */
    int updateByPrimaryKeySelective(RoleInfo record);

    /**
     *  根据主键来更新符合条件的数据库记录,role_info
     *
     * @param record
     */
    int updateByPrimaryKey(RoleInfo record);
}