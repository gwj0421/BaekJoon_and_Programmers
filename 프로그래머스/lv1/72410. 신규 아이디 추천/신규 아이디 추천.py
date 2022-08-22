import re
def solution(new_id):
    new_id=new_id.lower()
    new_id=re.sub(r"[^a-z0-9\-_.]","",new_id)
    new_id=re.sub(r"\.+",".",new_id)
    new_id=re.sub(r"^\.|\.$","",new_id)

    length=len(new_id)
    new_id=re.sub("\.$","",new_id[:15]) if length>15 else new_id+new_id[-1]*(3-len(new_id)) if length>0 & length<3  else 'aaa' if length==0 else new_id

    return new_id