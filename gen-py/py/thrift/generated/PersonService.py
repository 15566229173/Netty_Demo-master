#
# Autogenerated by Thrift Compiler (0.12.0)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#
#  options string: py
#

from thrift.Thrift import TType, TMessageType, TFrozenDict, TException, TApplicationException
from thrift.protocol.TProtocol import TProtocolException
from thrift.TRecursive import fix_spec

import sys
import logging
from .ttypes import *
from thrift.Thrift import TProcessor
from thrift.transport import TTransport
all_structs = []


class Iface(object):
    def getPersonByName(self, name):
        """
        Parameters:
         - name

        """
        pass

    def savePerson(self, person):
        """
        Parameters:
         - person

        """
        pass


class Client(Iface):
    def __init__(self, iprot, oprot=None):
        self._iprot = self._oprot = iprot
        if oprot is not None:
            self._oprot = oprot
        self._seqid = 0

    def getPersonByName(self, name):
        """
        Parameters:
         - name

        """
        self.send_getPersonByName(name)
        return self.recv_getPersonByName()

    def send_getPersonByName(self, name):
        self._oprot.writeMessageBegin('getPersonByName', TMessageType.CALL, self._seqid)
        args = getPersonByName_args()
        args.name = name
        args.write(self._oprot)
        self._oprot.writeMessageEnd()
        self._oprot.trans.flush()

    def recv_getPersonByName(self):
        iprot = self._iprot
        (fname, mtype, rseqid) = iprot.readMessageBegin()
        if mtype == TMessageType.EXCEPTION:
            x = TApplicationException()
            x.read(iprot)
            iprot.readMessageEnd()
            raise x
        result = getPersonByName_result()
        result.read(iprot)
        iprot.readMessageEnd()
        if result.success is not None:
            return result.success
        if result.dateException is not None:
            raise result.dateException
        raise TApplicationException(TApplicationException.MISSING_RESULT, "getPersonByName failed: unknown result")

    def savePerson(self, person):
        """
        Parameters:
         - person

        """
        self.send_savePerson(person)
        self.recv_savePerson()

    def send_savePerson(self, person):
        self._oprot.writeMessageBegin('savePerson', TMessageType.CALL, self._seqid)
        args = savePerson_args()
        args.person = person
        args.write(self._oprot)
        self._oprot.writeMessageEnd()
        self._oprot.trans.flush()

    def recv_savePerson(self):
        iprot = self._iprot
        (fname, mtype, rseqid) = iprot.readMessageBegin()
        if mtype == TMessageType.EXCEPTION:
            x = TApplicationException()
            x.read(iprot)
            iprot.readMessageEnd()
            raise x
        result = savePerson_result()
        result.read(iprot)
        iprot.readMessageEnd()
        if result.dataException is not None:
            raise result.dataException
        return


class Processor(Iface, TProcessor):
    def __init__(self, handler):
        self._handler = handler
        self._processMap = {}
        self._processMap["getPersonByName"] = Processor.process_getPersonByName
        self._processMap["savePerson"] = Processor.process_savePerson

    def process(self, iprot, oprot):
        (name, type, seqid) = iprot.readMessageBegin()
        if name not in self._processMap:
            iprot.skip(TType.STRUCT)
            iprot.readMessageEnd()
            x = TApplicationException(TApplicationException.UNKNOWN_METHOD, 'Unknown function %s' % (name))
            oprot.writeMessageBegin(name, TMessageType.EXCEPTION, seqid)
            x.write(oprot)
            oprot.writeMessageEnd()
            oprot.trans.flush()
            return
        else:
            self._processMap[name](self, seqid, iprot, oprot)
        return True

    def process_getPersonByName(self, seqid, iprot, oprot):
        args = getPersonByName_args()
        args.read(iprot)
        iprot.readMessageEnd()
        result = getPersonByName_result()
        try:
            result.success = self._handler.getPersonByName(args.name)
            msg_type = TMessageType.REPLY
        except TTransport.TTransportException:
            raise
        except DataException as dateException:
            msg_type = TMessageType.REPLY
            result.dateException = dateException
        except TApplicationException as ex:
            logging.exception('TApplication exception in handler')
            msg_type = TMessageType.EXCEPTION
            result = ex
        except Exception:
            logging.exception('Unexpected exception in handler')
            msg_type = TMessageType.EXCEPTION
            result = TApplicationException(TApplicationException.INTERNAL_ERROR, 'Internal error')
        oprot.writeMessageBegin("getPersonByName", msg_type, seqid)
        result.write(oprot)
        oprot.writeMessageEnd()
        oprot.trans.flush()

    def process_savePerson(self, seqid, iprot, oprot):
        args = savePerson_args()
        args.read(iprot)
        iprot.readMessageEnd()
        result = savePerson_result()
        try:
            self._handler.savePerson(args.person)
            msg_type = TMessageType.REPLY
        except TTransport.TTransportException:
            raise
        except DataException as dataException:
            msg_type = TMessageType.REPLY
            result.dataException = dataException
        except TApplicationException as ex:
            logging.exception('TApplication exception in handler')
            msg_type = TMessageType.EXCEPTION
            result = ex
        except Exception:
            logging.exception('Unexpected exception in handler')
            msg_type = TMessageType.EXCEPTION
            result = TApplicationException(TApplicationException.INTERNAL_ERROR, 'Internal error')
        oprot.writeMessageBegin("savePerson", msg_type, seqid)
        result.write(oprot)
        oprot.writeMessageEnd()
        oprot.trans.flush()

# HELPER FUNCTIONS AND STRUCTURES


class getPersonByName_args(object):
    """
    Attributes:
     - name

    """


    def __init__(self, name=None,):
        self.name = name

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.STRING:
                    self.name = iprot.readString().decode('utf-8') if sys.version_info[0] == 2 else iprot.readString()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('getPersonByName_args')
        if self.name is not None:
            oprot.writeFieldBegin('name', TType.STRING, 1)
            oprot.writeString(self.name.encode('utf-8') if sys.version_info[0] == 2 else self.name)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        if self.name is None:
            raise TProtocolException(message='Required field name is unset!')
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)
all_structs.append(getPersonByName_args)
getPersonByName_args.thrift_spec = (
    None,  # 0
    (1, TType.STRING, 'name', 'UTF8', None, ),  # 1
)


class getPersonByName_result(object):
    """
    Attributes:
     - success
     - dateException

    """


    def __init__(self, success=None, dateException=None,):
        self.success = success
        self.dateException = dateException

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 0:
                if ftype == TType.STRUCT:
                    self.success = Person()
                    self.success.read(iprot)
                else:
                    iprot.skip(ftype)
            elif fid == 1:
                if ftype == TType.STRUCT:
                    self.dateException = DataException()
                    self.dateException.read(iprot)
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('getPersonByName_result')
        if self.success is not None:
            oprot.writeFieldBegin('success', TType.STRUCT, 0)
            self.success.write(oprot)
            oprot.writeFieldEnd()
        if self.dateException is not None:
            oprot.writeFieldBegin('dateException', TType.STRUCT, 1)
            self.dateException.write(oprot)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)
all_structs.append(getPersonByName_result)
getPersonByName_result.thrift_spec = (
    (0, TType.STRUCT, 'success', [Person, None], None, ),  # 0
    (1, TType.STRUCT, 'dateException', [DataException, None], None, ),  # 1
)


class savePerson_args(object):
    """
    Attributes:
     - person

    """


    def __init__(self, person=None,):
        self.person = person

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.STRUCT:
                    self.person = Person()
                    self.person.read(iprot)
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('savePerson_args')
        if self.person is not None:
            oprot.writeFieldBegin('person', TType.STRUCT, 1)
            self.person.write(oprot)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        if self.person is None:
            raise TProtocolException(message='Required field person is unset!')
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)
all_structs.append(savePerson_args)
savePerson_args.thrift_spec = (
    None,  # 0
    (1, TType.STRUCT, 'person', [Person, None], None, ),  # 1
)


class savePerson_result(object):
    """
    Attributes:
     - dataException

    """


    def __init__(self, dataException=None,):
        self.dataException = dataException

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.STRUCT:
                    self.dataException = DataException()
                    self.dataException.read(iprot)
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('savePerson_result')
        if self.dataException is not None:
            oprot.writeFieldBegin('dataException', TType.STRUCT, 1)
            self.dataException.write(oprot)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)
all_structs.append(savePerson_result)
savePerson_result.thrift_spec = (
    None,  # 0
    (1, TType.STRUCT, 'dataException', [DataException, None], None, ),  # 1
)
fix_spec(all_structs)
del all_structs

