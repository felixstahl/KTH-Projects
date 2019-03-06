defmodule Env do

  def test() do
    env = new()
    add(6, :ima, env)
    add(7, :bloc, env)
    add(8, :boi, env)
    lookup(6,env)
    remove([6,7], env)
    env
  end
  def new() do [] end

  def add(id, str, env) do [{id, str} | env] end

  def lookup(id, env) do
    if List.keymember?(env, id, 0) do List.keyfind(env, id, 0)
    else nil end
  end

  def remove([], env) do env end
  def remove([fid | rest], env) do
    remove(rest, List.keydelete(env, fid, 0))
  end
   #2.2 exercise: {:a, {x, :b}} = {{:atm, a}, {{:var, x}, {:atm, b}}}
end
