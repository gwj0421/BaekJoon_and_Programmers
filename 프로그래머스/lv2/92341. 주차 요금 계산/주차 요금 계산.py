import math
def charge_func(duration,basic_time,basic_fee,unit_time,unit_fee):
    if duration > basic_time:
        return basic_fee + (duration-basic_time)//unit_time*unit_fee
    else:
        return basic_fee

def solution(fees, records):
    answer = []
    basic_time,basic_fee,unit_time,unit_fee = fees
    record_dict = {}
    not_out = {}
    for i in records:
        _t,_car,_situation = i.split()
        _t = int(_t[:2])*60+int(_t[-2:])
        if _situation =='IN':
            not_out[_car] = _t
        else:
            duration = _t - not_out[_car]
            print(_car,duration)
            temp = record_dict.get(_car,0)
            record_dict[_car] = temp + duration
            not_out.pop(_car)

    end_time = 23*60+59
    for i,j in not_out.items():
        duration = end_time - j
        temp = record_dict.get(i,0)
        record_dict[i] = temp + duration

    print(record_dict)
    for i,j in record_dict.items():
        if j > basic_time:
            print(i,j,'---')
            charge = basic_fee+math.ceil((j-basic_time)/unit_time)*unit_fee
        else:
            charge = basic_fee
        record_dict[i] = charge
        print(charge)
    temp = sorted(record_dict.items())
    temp = [x[1] for x in temp]
    return temp