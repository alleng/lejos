/* Auto-generated by genmsg_cpp for file /home/lejosdev/ros/workspace/nxt_lejos/nxt_lejos_msgs/msg/JointPosition.msg */
#ifndef NXT_LEJOS_MSGS_MESSAGE_JOINTPOSITION_H
#define NXT_LEJOS_MSGS_MESSAGE_JOINTPOSITION_H
#include <string>
#include <vector>
#include <map>
#include <ostream>
#include "ros/serialization.h"
#include "ros/builtin_message_traits.h"
#include "ros/message_operations.h"
#include "ros/time.h"

#include "ros/macros.h"

#include "ros/assert.h"


namespace nxt_lejos_msgs
{
template <class ContainerAllocator>
struct JointPosition_ {
  typedef JointPosition_<ContainerAllocator> Type;

  JointPosition_()
  : name()
  , angle(0.0)
  {
  }

  JointPosition_(const ContainerAllocator& _alloc)
  : name(_alloc)
  , angle(0.0)
  {
  }

  typedef std::basic_string<char, std::char_traits<char>, typename ContainerAllocator::template rebind<char>::other >  _name_type;
  std::basic_string<char, std::char_traits<char>, typename ContainerAllocator::template rebind<char>::other >  name;

  typedef double _angle_type;
  double angle;


private:
  static const char* __s_getDataType_() { return "nxt_lejos_msgs/JointPosition"; }
public:
  ROS_DEPRECATED static const std::string __s_getDataType() { return __s_getDataType_(); }

  ROS_DEPRECATED const std::string __getDataType() const { return __s_getDataType_(); }

private:
  static const char* __s_getMD5Sum_() { return "882c10e7eced8d306af293350cdb9414"; }
public:
  ROS_DEPRECATED static const std::string __s_getMD5Sum() { return __s_getMD5Sum_(); }

  ROS_DEPRECATED const std::string __getMD5Sum() const { return __s_getMD5Sum_(); }

private:
  static const char* __s_getMessageDefinition_() { return "string name\n\
float64 angle\n\
\n\
"; }
public:
  ROS_DEPRECATED static const std::string __s_getMessageDefinition() { return __s_getMessageDefinition_(); }

  ROS_DEPRECATED const std::string __getMessageDefinition() const { return __s_getMessageDefinition_(); }

  ROS_DEPRECATED virtual uint8_t *serialize(uint8_t *write_ptr, uint32_t seq) const
  {
    ros::serialization::OStream stream(write_ptr, 1000000000);
    ros::serialization::serialize(stream, name);
    ros::serialization::serialize(stream, angle);
    return stream.getData();
  }

  ROS_DEPRECATED virtual uint8_t *deserialize(uint8_t *read_ptr)
  {
    ros::serialization::IStream stream(read_ptr, 1000000000);
    ros::serialization::deserialize(stream, name);
    ros::serialization::deserialize(stream, angle);
    return stream.getData();
  }

  ROS_DEPRECATED virtual uint32_t serializationLength() const
  {
    uint32_t size = 0;
    size += ros::serialization::serializationLength(name);
    size += ros::serialization::serializationLength(angle);
    return size;
  }

  typedef boost::shared_ptr< ::nxt_lejos_msgs::JointPosition_<ContainerAllocator> > Ptr;
  typedef boost::shared_ptr< ::nxt_lejos_msgs::JointPosition_<ContainerAllocator>  const> ConstPtr;
  boost::shared_ptr<std::map<std::string, std::string> > __connection_header;
}; // struct JointPosition
typedef  ::nxt_lejos_msgs::JointPosition_<std::allocator<void> > JointPosition;

typedef boost::shared_ptr< ::nxt_lejos_msgs::JointPosition> JointPositionPtr;
typedef boost::shared_ptr< ::nxt_lejos_msgs::JointPosition const> JointPositionConstPtr;


template<typename ContainerAllocator>
std::ostream& operator<<(std::ostream& s, const  ::nxt_lejos_msgs::JointPosition_<ContainerAllocator> & v)
{
  ros::message_operations::Printer< ::nxt_lejos_msgs::JointPosition_<ContainerAllocator> >::stream(s, "", v);
  return s;}

} // namespace nxt_lejos_msgs

namespace ros
{
namespace message_traits
{
template<class ContainerAllocator> struct IsMessage< ::nxt_lejos_msgs::JointPosition_<ContainerAllocator> > : public TrueType {};
template<class ContainerAllocator> struct IsMessage< ::nxt_lejos_msgs::JointPosition_<ContainerAllocator>  const> : public TrueType {};
template<class ContainerAllocator>
struct MD5Sum< ::nxt_lejos_msgs::JointPosition_<ContainerAllocator> > {
  static const char* value() 
  {
    return "882c10e7eced8d306af293350cdb9414";
  }

  static const char* value(const  ::nxt_lejos_msgs::JointPosition_<ContainerAllocator> &) { return value(); } 
  static const uint64_t static_value1 = 0x882c10e7eced8d30ULL;
  static const uint64_t static_value2 = 0x6af293350cdb9414ULL;
};

template<class ContainerAllocator>
struct DataType< ::nxt_lejos_msgs::JointPosition_<ContainerAllocator> > {
  static const char* value() 
  {
    return "nxt_lejos_msgs/JointPosition";
  }

  static const char* value(const  ::nxt_lejos_msgs::JointPosition_<ContainerAllocator> &) { return value(); } 
};

template<class ContainerAllocator>
struct Definition< ::nxt_lejos_msgs::JointPosition_<ContainerAllocator> > {
  static const char* value() 
  {
    return "string name\n\
float64 angle\n\
\n\
";
  }

  static const char* value(const  ::nxt_lejos_msgs::JointPosition_<ContainerAllocator> &) { return value(); } 
};

} // namespace message_traits
} // namespace ros

namespace ros
{
namespace serialization
{

template<class ContainerAllocator> struct Serializer< ::nxt_lejos_msgs::JointPosition_<ContainerAllocator> >
{
  template<typename Stream, typename T> inline static void allInOne(Stream& stream, T m)
  {
    stream.next(m.name);
    stream.next(m.angle);
  }

  ROS_DECLARE_ALLINONE_SERIALIZER;
}; // struct JointPosition_
} // namespace serialization
} // namespace ros

namespace ros
{
namespace message_operations
{

template<class ContainerAllocator>
struct Printer< ::nxt_lejos_msgs::JointPosition_<ContainerAllocator> >
{
  template<typename Stream> static void stream(Stream& s, const std::string& indent, const  ::nxt_lejos_msgs::JointPosition_<ContainerAllocator> & v) 
  {
    s << indent << "name: ";
    Printer<std::basic_string<char, std::char_traits<char>, typename ContainerAllocator::template rebind<char>::other > >::stream(s, indent + "  ", v.name);
    s << indent << "angle: ";
    Printer<double>::stream(s, indent + "  ", v.angle);
  }
};


} // namespace message_operations
} // namespace ros

#endif // NXT_LEJOS_MSGS_MESSAGE_JOINTPOSITION_H
